package com.example.androidlab

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//@Composable
//fun NavGraph() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "first_screen") {
//        // Здесь вы можете определить навигационные маршруты
//        // Например:
//        composable("first_screen") {
//            navController.navigate("Screen2")
//        }
//        composable("second_screen/{heroName}") { backStackEntry ->
//            val heroName = backStackEntry.arguments?.getString("heroName")
//           // SecondScreen()
//        }
//    }
//}

val imageUrls = listOf(
    R.drawable.deadpool,
    R.drawable.ironman,
    R.drawable.spider
)
val namesHeroes = listOf(
    "DeadPool",
    "Iron Man",
    "Spider Man"
)