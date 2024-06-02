package com.example.marvelapp.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.marvelapp.data.model.ui.UiResults
import com.example.marvelapp.navigation.Screen
import com.example.marvelapp.network.api.ApiStatus
import com.example.marvelapp.ui.theme.AppTheme

@Composable
fun ScreenAbout(navController: NavController, viewModel: DetailVM, cardId: Int) {
    val cardData by viewModel.cardDataModel.observeAsState()
    val status by viewModel.status.observeAsState()

    LaunchedEffect(key1 = cardId) {
        viewModel.getCardById(cardId)
    }

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
                    text = "Error loading cards",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }

            ApiStatus.DONE -> {
                cardData?.let { CardDetails(navController, it, cardId) }
            }

            else -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CardDetails(navController: NavController, cardData: UiResults, cardId: Int) {
    if (cardData.id == cardId) {
        Box(modifier = Modifier.fillMaxSize()) {
            cardData.let { cardInfo ->
                AsyncImage(
                    model = cardInfo.thumbnail,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Button(
                    onClick = { navController.navigate(Screen.MainScreen.route) },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(AppTheme.Paddings.CardSpacer),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = AppTheme.ButtonColor.buttonColor
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(AppTheme.Paddings.CardHorizontalPadding)
                ) {
                    Column(Modifier.padding(AppTheme.Paddings.CardDetailTextPadding)) {
                        Text(
                            text = cardInfo.name,
                            style = AppTheme.TextStyle.Default_34,
                            color = AppTheme.TextColors.white,
                        )
                        Spacer(modifier = Modifier.height(AppTheme.Paddings.CardDetailSpacer))
                        Text(
                            text = cardInfo.description,
                            style = AppTheme.TextStyle.Default_24,
                            color = AppTheme.TextColors.white,
                        )
                    }
                }
            }
        }
    }
}
