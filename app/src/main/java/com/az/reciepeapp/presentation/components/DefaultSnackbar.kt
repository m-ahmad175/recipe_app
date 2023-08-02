package com.az.reciepeapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier, onDismiss: () -> Unit
) {

    SnackbarHost(modifier = modifier, hostState = snackbarHostState, snackbar = { data ->
        Snackbar(modifier = Modifier.padding(16.dp),
            action = {
            data.actionLabel?.let { 
                TextButton(onClick = { onDismiss()}) {
                    Text(text = it, style = MaterialTheme.typography.body2, color = Color.White)
                }
            }
        }) {
            Text(
                text = data.message,
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }
    })
}