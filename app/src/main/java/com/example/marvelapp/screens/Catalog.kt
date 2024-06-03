package com.example.marvelapp.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelapp.screens.details.MainHeader
import com.example.marvelapp.screens.details.CatalogCards
import com.example.marvelapp.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Catalog(scrollState: LazyListState, onClick: (Any) -> Unit) {
    ApplySystemBarColors()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.BgColor.bgColor)
    ) {
        Canvas(
            modifier = Modifier
                .size(AppTheme.Size.CanvasSize)
                .align(Alignment.BottomStart)
        ) {
            val path = Path()
            path.moveTo(size.width, 0f)
            path.lineTo(size.width, size.height)
            path.lineTo(0f, size.height)
            path.close()

            drawPath(path, color = AppTheme.BgColor.triangleColor)
        }
    }

    Column {
        MainHeader()
        CatalogCards(onClick, scrollState)
    }
}

@Composable
private fun ApplySystemBarColors() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = AppTheme.BgColor.transparent)
        systemUiController.setNavigationBarColor(color = AppTheme.BgColor.transparent)
    }
}

@Preview
@Composable
fun CatalogPreview() {
    Catalog(scrollState = LazyListState(), onClick = {})
}