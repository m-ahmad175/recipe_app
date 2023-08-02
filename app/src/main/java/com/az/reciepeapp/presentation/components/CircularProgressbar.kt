package com.az.reciepeapp.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

// decoupling

@Composable
fun CircularProgressBar(isDisplayed: Boolean) {

    if (isDisplayed) {

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            val constraints = if (minWidth < 600.dp) {
                myDecoupleConstraints(0.2f)
            } else {
                myDecoupleConstraints(0.5f)
            }

            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
                constraintSet = constraints
            ) {

                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.layoutId("progressBar"),
                )
                Text(
                    text = "",
                    style = TextStyle(color = Color.Black, fontSize = 12.sp),
                    modifier = Modifier.layoutId("text")
                )
            }
        }

    }
}

private fun myDecoupleConstraints(verticalBias: Float): ConstraintSet {
    return ConstraintSet {

        val guideline = createGuidelineFromTop(verticalBias)
        val progressBar = createRefFor("progressBar")
        val txt = createRefFor("text")

        constrain(progressBar) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(txt) {

            top.linkTo(progressBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}