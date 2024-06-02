package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.marvelapp.navigation.Navigation
import com.example.marvelapp.ui.screens.detail.DetailVM
import com.example.marvelapp.ui.screens.marvel.CatalogVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val CatalogVM: CatalogVM by viewModels()
    private val detailVM: DetailVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Navigation(CatalogVM, detailVM)
        }
    }
}
