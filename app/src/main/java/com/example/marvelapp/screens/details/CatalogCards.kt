package com.example.marvelapp.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelapp.screens.cards_info.ConcreteCard
import com.example.marvelapp.screens.cards_info.ConcreteCards
import com.example.marvelapp.ui.theme.AppTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogCards(onClick: (Int) -> Unit, scrollState: LazyListState) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)
    ) {
        items(ConcreteCards) { ConcreteCard ->
            ConcreteCardOp(ConcreteCard = ConcreteCard, onClick)
        }
    }
}


@Composable
fun ConcreteCardOp(ConcreteCard: ConcreteCard, onClick: (Int) -> Unit = {}) {
    val isHover = remember { mutableStateOf(false) }

    val backgroundColor = if (isHover.value) {
        AppTheme.BgColor.hover
    } else {
        AppTheme.BgColor.transparent
    }

    Box(
        modifier = Modifier
            .size(height = AppTheme.Size.ConcreteCardOpHeight,
                width = AppTheme.Size.ConcreteCardOpWidth)
            .padding(AppTheme.Paddings.ConcreteCardOpPadding)
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable { isHover.value = !isHover.value
                onClick(ConcreteCard.id) }
            .background(backgroundColor),
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = ConcreteCard.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = stringResource(id = ConcreteCard.name),
            style = AppTheme.TextStyle.Default_28,
            color = AppTheme.TextColors.white,
            modifier = Modifier
                .padding(AppTheme.Paddings.PaddingValues_ConcreteCardOp)
        )
    }
}


@Preview
@Composable
fun CatalogCardsPreview() {
    CatalogCards(onClick = {}, scrollState = LazyListState())
}