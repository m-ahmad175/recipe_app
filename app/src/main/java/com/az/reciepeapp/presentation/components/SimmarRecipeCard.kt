package com.az.reciepeapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.az.reciepeapp.presentation.components.utils.shimmerEffect

@Composable
fun ShimmerRecipeCard(color: List<Color>, imageHeight: Dp) {

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Surface(shape = MaterialTheme.shapes.small) {

            Spacer(
                modifier = Modifier
                    .shimmerEffect(color)
                    .fillMaxWidth()
                    .height(imageHeight)
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight / 8)
                    .shimmerEffect(color)
            )
        }
    }

}