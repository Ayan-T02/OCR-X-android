package com.ayant02.ocrx.ui.home

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun SessionDialog(
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit
) {

    var sessionName by remember {

        mutableStateOf("")

    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text("New Session")

        },

        text = {

            OutlinedTextField(

                value = sessionName,

                onValueChange = {

                    sessionName = it

                },

                label = {

                    Text("Session Name")

                }

            )

        },

        confirmButton = {

            Button(

                onClick = {

                    onCreate(sessionName)

                }

            ) {

                Text("Start")

            }

        }

    )

}