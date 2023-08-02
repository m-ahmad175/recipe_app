package com.az.reciepeapp.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.az.reciepeapp.R

private val IBMPlex = FontFamily(
    Font(R.font.ibmplexserif_light, FontWeight.W300),
    Font(R.font.ibmplexserif_regular, FontWeight.W400),
    Font(R.font.ibmplexserif_medium, FontWeight.W500),
    Font(R.font.ibmplexserif_bold, FontWeight.W600)
)

val IBMPlexTypography = androidx.compose.material.Typography(

    h1 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),

    h2 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),

    h3 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),

    h4 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),

    h5 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),

    h6 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = IBMPlex,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        color = Color.White
    ),
    caption = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = IBMPlex,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)