package com.az.reciepeapp.presentation.components.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmerEffect(color: List<Color>): Modifier = composed {

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(), animationSpec = infiniteRepeatable(
            animation = tween(
                1300,
                delayMillis = 500,
                easing = LinearEasing
            )
        )
    )
    val brush = Brush.linearGradient(

        color,
        start = Offset(startOffset, 0f),
        end = Offset(startOffset + size.width.toFloat(), size.height.toFloat())
    )
    background(
        brush,
    )
        .onGloballyPositioned {
            size = it.size
        }

}