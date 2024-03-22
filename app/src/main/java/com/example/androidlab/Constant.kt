package com.example.androidlab

import androidx.compose.ui.unit.sp

data class Hero(
    val imageUrl: Int, val name: String, val phrase: String
)

object Constant {
    val size = 3
    val HomeScreen = "FirstScreen"
    val DetailScreen = "SecondScreen"
    val ChooseHero = "Choose your hero"

    val averFont = 28.sp
    val bigFont = 36.sp

    val heroes = listOf(
        Hero(R.drawable.deadpool, "DeadPool", "Please don’t make the supersuit green...or animated!"),
        Hero(R.drawable.ironman, "Iron Man", "You know who I am"),
        Hero(R.drawable.spider, "Spider Man", "In iron suit")
    )
}