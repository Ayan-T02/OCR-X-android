package com.ayant02.ocrx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ayant02.ocrx.navigation.AppNavigation
import com.ayant02.ocrx.ui.theme.OCRXTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OCRXTheme {
                AppNavigation()
            }
        }
    }
}