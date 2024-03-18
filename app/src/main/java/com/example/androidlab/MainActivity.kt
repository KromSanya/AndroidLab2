package com.example.androidlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidlab.ui.theme.AndroidLabTheme


import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import coil.compose.AsyncImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AndroidLabTheme {
                SetupNavGraph(navController)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstScreen(navController: NavController) {
    Column(
        Modifier.background(color = Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val state = rememberLazyListState()

        AsyncImage(
            model = R.drawable.ic_logo,
            contentDescription = null,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Text(
            text = Constant.ChooseHero,
            modifier = Modifier.padding(bottom = 20.dp),
            style = TextStyle(fontSize = Constant.bigFont, color = Color.White)
        )


        LazyRow(
            state = state, flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {

            items(Constant.size) { index ->
                Box(modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = screenHeight * 0.05f)
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(route = Constant.DetailScreen +"/$index")
                    }) {
                    AsyncImage(
                        model = Constant.heroes[index].imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxSize()
                    )
                    Text(
                        text = Constant.heroes[index].name,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart),
                        style = TextStyle(fontSize = Constant.bigFont)
                    )
                }
            }
        }

    }
}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun HeroSc(function: (Any?) -> Unit) {
//
//}