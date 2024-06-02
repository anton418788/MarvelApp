package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.marvelapp.navigation.Navigation
import com.example.marvelapp.network.rep.CatalogRep
import com.example.marvelapp.ui.screens.catalog.CatalogVM
import com.example.marvelapp.ui.screens.screen_about.ScreenAboutVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val CatalogVM: CatalogVM by viewModels()
    private val ScreenAboutVM: ScreenAboutVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Navigation(CatalogVM, ScreenAboutVM)
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCatalogVM(CatalogRep: CatalogRep): CatalogVM {
        return CatalogVM(CatalogRep)
    }

    @Provides
    @ViewModelScoped
    fun provideScreenAboutVM(CatalogRep: CatalogRep): ScreenAboutVM {
        return ScreenAboutVM(CatalogRep)
    }
}