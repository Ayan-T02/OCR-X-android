package com.ayant02.ocrx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayant02.ocrx.camera.CameraScreen
import com.ayant02.ocrx.ui.home.HomeScreen

object Routes {
    const val HOME = "home"
    const val CAMERA = "camera"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

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