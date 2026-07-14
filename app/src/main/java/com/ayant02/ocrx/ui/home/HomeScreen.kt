package com.ayant02.ocrx.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ayant02.ocrx.ui.components.PrimaryButton
import com.ayant02.ocrx.ui.components.TopBar

@Composable
fun HomeScreen(
    onNewSessionClick: (String) -> Unit = {}
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8FAFC),
                            Color(0xFFE8F1FF)
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically(
                            initialOffsetY = { it / 2 }
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(24.dp)
                        ) {

                            TopBar()

                        }

                    }

                }

                Spacer(modifier = Modifier.height(28.dp))

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically()
                ) {

                    PrimaryButton(
                        text = "Start New Scan"
                    ) {

                        showDialog = true

                    }

                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Recent Sessions",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "📄 College Notes",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.bodySmall
                        )

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "📄 Shopping List",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Yesterday",
                            style = MaterialTheme.typography.bodySmall
                        )

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "📄 Receipts",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "2 Days Ago",
                            style = MaterialTheme.typography.bodySmall
                        )

                    }

                }

            }

        }

    }

    if (showDialog) {

        SessionDialog(

            onDismiss = {

                showDialog = false

            },

            onCreate = { sessionName ->

                showDialog = false

                onNewSessionClick(sessionName)

            }

        )

    }

}