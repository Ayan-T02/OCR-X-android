package com.ayant02.ocrx.camera

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun CameraScreen() {
    var detectedText by remember { mutableStateOf("") }
    var candidateText by remember { mutableStateOf("") }
    var candidateCount by remember { mutableStateOf(0) }
    var lastAcceptedAt by remember { mutableStateOf(0L) }
    val clipboardManager = LocalClipboardManager.current

    CameraPermission {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onTextDetected = { rawText ->
                        val normalizedText = rawText
                            .lineSequence()
                            .map { it.trim() }
                            .filter { it.isNotBlank() }
                            .joinToString(separator = "\n")
                            .trim()

                        if (normalizedText.length >= 3) {
                            if (normalizedText == candidateText) {
                                candidateCount += 1
                            } else {
                                candidateText = normalizedText
                                candidateCount = 1
                            }

                            val now = System.currentTimeMillis()
                            val shouldAccept =
                                candidateCount >= 2 || now - lastAcceptedAt > 1200

                            if (shouldAccept && normalizedText != detectedText) {
                                detectedText = normalizedText
                                lastAcceptedAt = now
                            }
                        }
                    }
                )

                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    visible = true,
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
                                text = "Live OCR",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Real-time ML Kit text recognition",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Crossfade(
                                targetState = detectedText.ifBlank {
                                    "Point the camera at a document or printed text."
                                },
                                label = "ocrText"
                            ) { text ->
                                Text(
                                    text = text,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 120.dp, max = 220.dp)
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
                                        clipboardManager.setText(
                                            AnnotatedString(detectedText)
                                        )
                                    }
                                ) {
                                    Text("Copy")
                                }

                                OutlinedButton(
                                    modifier = Modifier.weight(1f),
                                    onClick = {
                                        detectedText = ""
                                        candidateText = ""
                                        candidateCount = 0
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
