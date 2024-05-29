package com.example.marvelapp.ui.theme.screens.catalog

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelapp.ui.theme.AppTheme
import com.example.marvelapp.ui.theme.screens.details.CatalogCards
import com.example.marvelapp.ui.theme.screens.details.MainHeader
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Catalog(CatalogVM: CatalogVM, lazyListState: LazyListState, onClick: (Int) -> Unit) {
    ApplySystemBarColors()

    val heroes by CatalogVM.heroesDataModel.observeAsState()
    val status by CatalogVM.status.observeAsState()

    LaunchedEffect(key1 = true) {
        CatalogVM.getResponseList()
    }

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
        CatalogCards(heroes?.data, status, onItemClick = onClick, lazyListState = lazyListState)
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

@Preview(showBackground = true)
@Composable
fun PreviewMarvelScreen() {
    //MarvelScreen(navController = rememberNavController())
}