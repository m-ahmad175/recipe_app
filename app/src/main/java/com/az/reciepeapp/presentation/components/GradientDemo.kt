package com.az.reciepeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color

@Composable
fun GradientDemo(){

    val colors = listOf(
        Color.Gray,
        Color.White,
        Color.Gray
    )

    val brush = linearGradient(

        colors,
        start = Offset(200f, 200f),
        end = Offset(400f, 400f)
    )
    Surface(shape = MaterialTheme.shapes.small) {

        Spacer(modifier = Modifier.fillMaxSize().background(brush))
    }
}