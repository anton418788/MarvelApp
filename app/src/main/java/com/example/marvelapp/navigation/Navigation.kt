package com.example.marvelapp.navigation

import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelapp.MainActivity
import com.example.marvelapp.ui.screens.detail.ScreenAbout
import com.example.marvelapp.ui.screens.detail.DetailVM
import com.example.marvelapp.ui.screens.marvel.CatalogScreen
import com.example.marvelapp.ui.screens.marvel.CatalogVM

@Composable
fun Navigation(
    CatalogVM: CatalogVM,
    detailVM: DetailVM
) {
    val navController = rememberNavController()

    val context = LocalContext.current
    val onBackPressedDispatcher = (context as MainActivity).onBackPressedDispatcher

    val lazyListState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            CatalogScreen(CatalogVM, lazyListState) { cardId ->
                navController.navigate(Screen.ScreenAbout.route + "/$cardId")
            }
        }

        composable(
            route = "${Screen.ScreenAbout.route}/{cardId}",
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getInt("cardId") ?: -1
            ScreenAbout(navController, detailVM, cardId)
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(Screen.MainScreen.route)
                }
            }
            onBackPressedDispatcher.addCallback(callback)
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object ScreenAbout : Screen("detail_screen")
}