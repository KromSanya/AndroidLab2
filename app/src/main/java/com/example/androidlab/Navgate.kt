package com.example.androidlab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Home : Screen(route = "screen1")
    object Detail : Screen(route = "SecondScreen/{index}")
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
            FirstScreen (
                navController = navController
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
            ){
            SecondScreen(navController = navController)
        }
    }
}

val imageUrls = listOf(
    R.drawable.deadpool,
    R.drawable.ironman,
    R.drawable.spider
)
val names = listOf(
    "DeadPool",
    "Iron Man",
    "Spider Man"
)
val heroesPhrase = listOf(
    "Please don’t make the supersuit green...or animated!",
    "You know who I'am",
    "In iron suit"
)