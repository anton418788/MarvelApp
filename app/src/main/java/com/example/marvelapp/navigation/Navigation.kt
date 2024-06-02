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
import com.example.marvelapp.ui.screens.catalog.Catalog
import com.example.marvelapp.ui.screens.catalog.CatalogVM
import com.example.marvelapp.ui.screens.screen_about.ScreenAbout
import com.example.marvelapp.ui.screens.screen_about.ScreenAboutVM

@Composable
fun Navigation(
    catalogVM: CatalogVM,
    screenAboutVM: ScreenAboutVM
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
            Catalog(catalogVM, lazyListState) { heroId ->
                navController.navigate(Screen.ScreenAbout.route + "/$heroId")
            }
        }


        composable(
            route = "${Screen.ScreenAbout.route}/{heroId}",
            arguments = listOf(navArgument("heroId") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("heroId") ?: -1
            ScreenAbout(navController, screenAboutVM, heroId)
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
    data object ScreenAbout : Screen("screen_about")
}