package com.example.marvelapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.marvelapp.screens.cards_info.getConcreteCardById
import com.example.marvelapp.ui.theme.AppTheme

@Composable
fun ScreenAbout(ConcreteCardId: Int, onClick: () -> Unit) {
    val ConcreteCard = getConcreteCardById(ConcreteCardId)

    Box(modifier = Modifier.fillMaxSize()) {
        if (ConcreteCard != null) {
            AsyncImage(
                model = ConcreteCard.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(AppTheme.Paddings.ConcreteCardDetailButton),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = AppTheme.ButtonColor.buttonColor
            )
        }
        if (ConcreteCard != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(AppTheme.Paddings.ConcreteCardDetailHorizontalPadding)
            ) {
                Column(Modifier.padding(AppTheme.Paddings.ConcreteCardDetailTextPadding)) {
                    Text(
                        text = stringResource(id = ConcreteCard.name),
                        style = AppTheme.TextStyle.Default_34,
                        color = AppTheme.TextColors.white,
                    )
                    Spacer(modifier = Modifier.height(AppTheme.Paddings.ConcreteCardDetailSpacer))
                    Text(
                        text = stringResource(id = ConcreteCard.info),
                        style = AppTheme.TextStyle.Default_24,
                        color = AppTheme.TextColors.white,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenAboutPreview() {
    ScreenAbout(ConcreteCardId = 3, onClick = {})
}