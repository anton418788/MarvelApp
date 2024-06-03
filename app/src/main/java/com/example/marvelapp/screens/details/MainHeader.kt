package com.example.marvelapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelapp.R
import com.example.marvelapp.ui.theme.AppTheme

@Composable
fun MainHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.Paddings.MainHeaderPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "",
            modifier = Modifier.size(
                width = AppTheme.Size.MainHeaderImageWidth,
                height = AppTheme.Size.MainHeaderImageHeight
            )
        )
        Spacer(modifier = Modifier.height(AppTheme.Paddings.MainHeaderSpacer))
        Text(
            text = stringResource(id = R.string.header),
            style = AppTheme.TextStyle.Default_28,
            color = AppTheme.TextColors.white,
        )
    }
}


@Preview
@Composable
fun MainHeaderPreview() {
    MainHeader()
}