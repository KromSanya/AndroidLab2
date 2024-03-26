package com.example.androidlab

import androidx.compose.ui.unit.sp

data class Hero(
    val imageUrl: Int, val name: String, val phrase: String
)

object Constant {

    const val HomeScreen = "FirstScreen"
    const val DetailScreen = "SecondScreen"
    const val ChooseHero = "Choose your hero"

    val averFont = 28.sp
    val bigFont = 36.sp

    val deadpool = Hero(
        R.drawable.deadpool,
        "DeadPool",
        "Please don’t make the supersuit green...or animated!"
    )
    val ironman = Hero(
        R.drawable.ironman,
        "Iron Man",
        "You know who I am"
    )
    val spiderman = Hero(
        R.drawable.spider,
        "Spider Man",
        "In iron suit"
    )
    val heroes = listOf(
        deadpool,
        ironman,
        spiderman
    )
    val size = heroes.size
}