package com.ayant02.ocrx.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayant02.ocrx.camera.CameraScreen
import com.ayant02.ocrx.data.database.DatabaseProvider
import com.ayant02.ocrx.data.repository.OCRRepository
import com.ayant02.ocrx.ui.history.SessionDetailScreen
import com.ayant02.ocrx.ui.home.HomeScreen
import com.ayant02.ocrx.ui.splash.SplashScreen
import com.ayant02.ocrx.viewmodel.SessionViewModel

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val CAMERA = "camera"
    const val SESSION_DETAIL = "session"
}

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val database = remember {
        DatabaseProvider.getDatabase(context)
    }
    val repository = remember {
        OCRRepository(
            sessionDao = database.sessionDao(),
            textDao = database.ocrTextDao()
        )
    }
    val sessionViewModel: SessionViewModel = viewModel(
        factory = SessionViewModel.Factory(repository)
    )

    val navController = rememberNavController()
    val sessions by sessionViewModel.sessions.collectAsState()
    val sessionTexts by sessionViewModel.sessionTexts.collectAsState()
    val currentSessionTitle by sessionViewModel.currentSessionTitle.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                sessions = sessions,
                onNewSessionClick = { sessionName ->
                    sessionViewModel.createSession(sessionName) {
                        navController.navigate(Routes.CAMERA)
                    }
                },
                onSessionClick = { session ->
                    navController.navigate("${Routes.SESSION_DETAIL}/${session.id}")
                }
            )
        }

        composable(Routes.CAMERA) {
            CameraScreen(
                sessionTitle = currentSessionTitle,
                onStableTextDetected = sessionViewModel::saveDetectedText
            )
        }

        composable(
            route = "${Routes.SESSION_DETAIL}/{sessionId}",
            arguments = listOf(
                navArgument("sessionId") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            val sessionId = entry.arguments?.getLong("sessionId") ?: 0L
            val sessionTitle = sessions
                .firstOrNull { it.id == sessionId }
                ?.title
                ?: "Session"

            androidx.compose.runtime.LaunchedEffect(sessionId) {
                sessionViewModel.loadSessionTexts(sessionId)
            }

            SessionDetailScreen(
                title = sessionTitle,
                texts = sessionTexts,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
