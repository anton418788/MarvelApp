package com.example.marvelapp.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppTheme {
    object TextColors {
        val white = Color(255,255,255)
    }

    object ButtonColor {
        val buttonColor = Color(255, 255, 255)
    }

    object BgColor {
        val bgColor = Color(41, 37, 41, 255)
        val triangleColor = Color(114, 17, 19, 255)
        val transparent = Color.Transparent
        val hover = Color.Red.copy(alpha = 0.5f)
    }

    object Paddings {
        val HeroCardPadding
            get() = 20.dp

        val PaddingValues_HeroCard
            @Composable
            get() =
                PaddingValues (start = 20.dp, bottom = 30.dp)
        val MainHeaderPadding
            @Composable
            get() =
                PaddingValues(top = 35.dp)
        val MainHeaderSpacer
            get() = 35.dp
        val HeroDetailButton
            @Composable
            get() =
                PaddingValues(top = 25.dp)
        val HeroDetailHorizontalPadding
            @Composable
            get() =
                PaddingValues(horizontal = 20.dp)
        val HeroDetailSpacer
            get() = 20.dp
        val HeroDetailTextPadding
            @Composable
            get() =
                PaddingValues(bottom = 35.dp)
    }

    object Size {
        val HeroCardHeight
            get() = 550.dp
        val HeroCardWidth
            get() = 300.dp
        val CanvasSize
            get() = 500.dp
        val MainHeaderImageHeight
            get() = 35.dp
        val MainHeaderImageWidth
            get() = 190.dp
    }

    object TextStyle {
        val Default_28
            @Composable get() =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W800,
                    fontSize = 28.sp
                )

        val Default_34
            @Composable get() =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W800,
                    fontSize = 34.sp
                )

        val Default_24
            @Composable get() =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W700,
                    fontSize = 24.sp
                )
    }
}