package com.example.marvelapp.ui

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelapp.data.model.ui.UiResults
import com.example.marvelapp.data.network.api.ApiStatus
import com.example.marvelapp.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogCards(
    cards: List<UiResults>,
    status: ApiStatus?,
    onItemClick: (Int) -> Unit,
    lazyListState: LazyListState)
{
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (status) {
            ApiStatus.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }

            ApiStatus.ERROR -> {
                Text(
                    text = "Error loading cards - check ur internet connection",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }

            ApiStatus.DONE -> {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    state = lazyListState,
                    flingBehavior = rememberSnapFlingBehavior(lazyListState)
                ) {
                    itemsIndexed(cards) { _, card ->
                        Cards(card = card, onItemClick = { onItemClick(card.id) })
                    }
                }
            }

            else -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun Cards(card: UiResults, onItemClick: (Int) -> Unit) {
    val isHover = remember { mutableStateOf(false) }

    val backgroundColor = if (isHover.value) {
        AppTheme.BgColor.hover
    } else {
        AppTheme.BgColor.transparent
    }

    Box(
        modifier = Modifier
            .size(
                height = AppTheme.Size.CardsHeight,
                width = AppTheme.Size.CardsWidth
            )
            .padding(AppTheme.Paddings.CardPadding)

            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                isHover.value = !isHover.value
                onItemClick(card.id)
            }
            .background(backgroundColor),
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = card.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = card.name,
            style = AppTheme.TextStyle.Default_28,
            color = AppTheme.TextColors.white,
            modifier = Modifier
                .padding(AppTheme.Paddings.CardsPadding)
        )
    }
}