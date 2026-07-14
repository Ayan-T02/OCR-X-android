package com.ayant02.ocrx.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.animation.Crossfade

@Composable
fun CameraScreen() {

    var detectedText by remember {
        mutableStateOf("Point camera at text...")
    }

    CameraPermission {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onTextDetected = {
                        detectedText = it
                    }
                )

                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.BottomCenter),

                    visible = detectedText.isNotBlank(),

                    enter = slideInVertically { it / 2 } + fadeIn(),

                    exit = slideOutVertically { it / 2 } + fadeOut()

                ) {

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        shape = RoundedCornerShape(28.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 18.dp
                        )

                    ) {
                        Column(

                            modifier = Modifier.padding(20.dp)

                        ) {

                            Text(

                                text = "text = \"\uD83D\uDD0D Live OCR\"",

                                style = MaterialTheme.typography.titleMedium

                            )
                            Text(
                                text = "Real-time ML Kit Text Recognition",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Spacer(modifier = Modifier.height(12.dp))

                            Crossfade(
                                targetState = detectedText,
                                label = "ocr"
                            ) { text ->

                                Text(
                                    text = text,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 120.dp)
                                        .verticalScroll(rememberScrollState()),
                                    style = MaterialTheme.typography.bodyMedium
                                )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(

                                horizontalArrangement = Arrangement.spacedBy(12.dp),

                                modifier = Modifier.fillMaxWidth()

                            ) {

                                Button(

                                    modifier = Modifier.weight(1f),

                                    onClick = {
                                        // Copy later
                                    }

                                ) {

                                    Text("Copy")

                                }

                                OutlinedButton(

                                    modifier = Modifier.weight(1f),

                                    onClick = {

                                        detectedText = ""

                                    }

                                ) {

                                    Text("Clear")

                                }

                            }

                        }

                    }

                }
            }
        }
    }
}