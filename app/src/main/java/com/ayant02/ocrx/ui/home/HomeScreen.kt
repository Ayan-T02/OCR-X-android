package com.ayant02.ocrx.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {

            TopBar()

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "➕ New Scan Session"
            ) {

                showDialog = true

            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                "Recent Sessions",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("📄 College Notes")

            Spacer(modifier = Modifier.height(8.dp))

            Text("📄 Shopping List")

            Spacer(modifier = Modifier.height(8.dp))

            Text("📄 Receipts")

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