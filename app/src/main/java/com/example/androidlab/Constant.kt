package com.example.androidlab

import androidx.compose.ui.unit.sp

object Constant {
    val HomeScreen = "FirstScreen"
    val DetailScreen = "SecondScreen"
    val ChooseHero = "Choose your hero"

    val averFont = 28.sp
    val bigFont = 36.sp

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
}