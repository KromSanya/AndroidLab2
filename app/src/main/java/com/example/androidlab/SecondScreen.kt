package com.example.androidlab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SecondScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val index = arguments?.getInt("index") ?: 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(((Constant.characterDetail?.thumbnail?.path ?: "https://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg") +"." + Constant.characters[index].thumbnail.extension).replace("http://", "https://"))
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
              //  .clip(RoundedCornerShape(20.dp))
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            Modifier.align(Alignment.BottomStart)
        ) {
            Text(
                text = Constant.characters[index].name,
                color = Color.White,
                modifier = Modifier
                    .padding(10.dp),
                style = TextStyle(fontSize = Constant.bigFont)
            )
            Constant.characterDetail?.let {
                Text(
                    text = it.description,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp),
                    style = TextStyle(fontSize = Constant.averFont)
                )
            }
        }
        
    }
}