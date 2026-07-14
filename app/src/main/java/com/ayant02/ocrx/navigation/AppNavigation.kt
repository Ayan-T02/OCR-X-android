package com.ayant02.ocrx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayant02.ocrx.camera.CameraScreen
import com.ayant02.ocrx.ui.home.HomeScreen
import com.ayant02.ocrx.ui.splash.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val CAMERA = "camera"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

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
                onNewSessionClick = { sessionName ->
                    println("Session: $sessionName")
                    navController.navigate(Routes.CAMERA)
                }
            )
        }

        composable(Routes.CAMERA) {
            CameraScreen()
        }
    }
}
