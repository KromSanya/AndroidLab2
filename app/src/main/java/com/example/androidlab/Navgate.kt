package com.example.androidlab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object Home : Screen(route = Constant.HomeScreen)
    data object Detail : Screen(route = Constant.DetailScreen + "/{index}")
}

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route)
    {
        composable(
            route = Screen.Home.route
        ) {
          //  MarvelApp(viewModel = MarvelViewModel())
            FirstScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {
            SecondScreen(navController = navController)
        }
    }
}

