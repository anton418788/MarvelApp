package com.example.marvelapp.ui.screens.catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marvelapp.network.response.ResponseStatus
import com.example.marvelapp.ui.components.DataModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogValidation(heroes: DataModel?, status: ResponseStatus?, onItemClick: (Int) -> Unit, lazyListState: LazyListState) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (status) {
            ResponseStatus.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }

            ResponseStatus.ERROR -> {
                Text(
                    text = "Error",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }

            ResponseStatus.DONE -> {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    state = lazyListState,
                    flingBehavior = rememberSnapFlingBehavior(lazyListState)
                ) {
                    heroes?.results?.let { heroesList ->
                        itemsIndexed(heroesList) { _, hero ->
                            HeroCard(hero = hero, onItemClick = { onItemClick(hero.id) })
                        }
                    }
                }
            }

            else -> {
                CircularProgressIndicator()
            }
        }
    }
}