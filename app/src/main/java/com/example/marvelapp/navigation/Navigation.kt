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
import com.example.marvelapp.ui.theme.screens.ScreenAbout
import com.example.marvelapp.ui.theme.screens.Catalog

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scrollState = rememberLazyListState()

    val context = LocalContext.current

    val onBackPressedDispatcher = (context as MainActivity).onBackPressedDispatcher

    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {}
    }
    onBackPressedDispatcher.addCallback(callback)


    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route

    ) {
        composable(Screen.MainScreen.route) {
            Catalog(scrollState) { ConcreteCardId ->
                navController.navigate(Screen.ScreenAbout.route + "/$ConcreteCardId")
            }
        }

        composable(
            Screen.ScreenAbout.route + "/{ConcreteCardId}",
            arguments = listOf(navArgument("ConcreteCardId") { type = NavType.IntType })
        ) { backStackEntry ->
            val ConcreteCardId = backStackEntry.arguments?.getInt("ConcreteCardId")
            if (ConcreteCardId != null) {
                ScreenAbout(ConcreteCardId = ConcreteCardId) {
                    navController.navigate(Screen.MainScreen.route)
                }

                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.navigate(Screen.MainScreen.route)
                    }
                }
                onBackPressedDispatcher.addCallback(callback)
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object ScreenAbout : Screen("detail_screen")
}