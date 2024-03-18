package com.example.androidlab

import android.content.ClipData.Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.example.androidlab.ui.theme.AndroidLabTheme


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidLabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Composable
fun MyApp(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = "https://iili.io/JMnuvbp.png",
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 20.dp)
        )


        Text(text = "Choose your hero", modifier = Modifier.padding(bottom = 20.dp), style = TextStyle(fontSize = 28.sp))

        MyImage()
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyImage() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val state = rememberLazyListState()

    val imageUrls = listOf(
        "https://iili.io/JMnAfIV.png",
        "https://iili.io/JMnuDI2.png",
        "https://iili.io/JMnuyB9.png"
    )
    val namesHeroes = listOf(
        "DeadPool",
        "Iron Man",
        "Spider Man"
    )

    LazyRow (
        Modifier.background(color = Color.Green),
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state)

    ) {

    items(imageUrls.size) { index ->
        Box(
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = screenHeight * 0.05f)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = imageUrls[index],
                contentDescription = null,
            )
            Text(
                text = namesHeroes[index],
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart),
                style = TextStyle(fontSize = 28.sp)
            )
        }
    }
    }
}