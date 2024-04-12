package com.app.compose_navigation_mvvm_flow.ui.composeui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SimpleAlertDialog(message:String) {
    var showDialog by remember {mutableStateOf(true)}
    if(showDialog) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                })
                { Text(text = "OK") }
            },
            title = { Text(text = "ReceipeApp") },
            text = { Text(text = message) }
        )
    }
}