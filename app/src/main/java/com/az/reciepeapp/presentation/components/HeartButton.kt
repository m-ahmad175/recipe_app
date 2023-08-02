package com.az.reciepeapp.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeartButton() {
    val checkState = remember { mutableStateOf(false) }

    IconToggleButton(checked = checkState.value, onCheckedChange = {
        checkState.value = !checkState.value
    }, modifier = Modifier.padding(10.dp)) {

        val transition = updateTransition(targetState = checkState.value, label = "")

        val tint by transition.animateColor(label = "iconColor") {
            if (it) Color.Red else Color.Black
        }

        val size by transition.animateDp(
            transitionSpec = {
                // on below line we are specifying transition
                if (false isTransitioningTo true) {
                    // on below line we are specifying key frames
                    keyframes {
                        // on below line we are specifying animation duration
                        durationMillis = 250
                        // on below line we are specifying animations.
                        30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                        35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                        40.dp at 75 // ms
                        35.dp at 150 // ms
                    }
                } else {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
            },
            label = "Size"
        ) {
            if (it) 30.dp else 28.dp
        }
        Icon(
            // on below line we are specifying icon for our image vector.
            imageVector = if (checkState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Icon",
            // on below line we are specifying
            // tint for our icon.
            tint = tint,
            // on below line we are specifying
            // size for our icon.
            modifier = Modifier.size(size)
        )
    }
}