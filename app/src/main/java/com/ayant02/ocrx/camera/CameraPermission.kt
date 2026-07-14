package com.ayant02.ocrx.camera

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

@Composable
fun CameraPermission(
    content: @Composable () -> Unit
) {
    var granted by remember {
        mutableStateOf(false)
    }

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            granted = it
        }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    if (granted) {
        content()
    }
}