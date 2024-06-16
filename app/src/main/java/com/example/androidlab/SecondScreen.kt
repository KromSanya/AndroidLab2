package com.example.androidlab

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.androidlab.Constant.characterDetail

@Composable
fun SecondScreen(
    navController: NavController,
    database: MainDb,
    viewModel: MarvelViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val index = arguments?.getInt("index") ?: 0

    val characterDetail by characterDetail.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        characterDetail?.let { character ->
            Log.d("FetchDetail", characterDetail.toString())
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        (character.thumbnailPath + "." + character.thumbnailExtension).replace(
                            "http://",
                            "https://"
                        )
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = character.name,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp),
                    style = TextStyle(fontSize = Constant.bigFont)
                )
                Text(
                    text = character.description,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp),
                    style = TextStyle(fontSize = Constant.averFont)
                )
            }
        }
    }
}
