package com.ayant02.ocrx.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {

    Column(
        modifier = Modifier.padding(24.dp)
    ) {

        Text(
            text = "OCR-X",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Smart Document Scanner",
            style = MaterialTheme.typography.bodyLarge
        )

    }

}

