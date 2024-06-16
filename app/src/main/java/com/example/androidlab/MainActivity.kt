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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidlab.Constant.characters

class MainActivity : ComponentActivity() {
    val database by lazy { MainDb.getDB(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AndroidLabTheme {
                SetupNavGraph(navController, database)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstScreen(
    navController: NavController,
    database: MainDb,
    viewModel: MarvelViewModel = MarvelViewModel()
) {

    val state = rememberLazyListState()
    val characters by characters.collectAsState()

    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo.lastOrNull() }
            .collect { lastVisibleItem ->
                if (lastVisibleItem == null || lastVisibleItem.index >= characters.size - 5) {
                    viewModel.fetchCharacters(database)
                }
            }
    }

    Column(
        Modifier.background(color = Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

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
            state = state,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
        ) {
            items(characters.size) { index ->
                val character = characters[index]
                Box(modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = screenHeight * 0.05f)
                    .fillMaxSize()
                    .clickable {
                        viewModel.fetchCharacterDetail(character.id, database)
                        navController.navigate(route = Constant.DetailScreen + "/${character.id}")
                    }) {
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
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(300.dp)
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = character.name,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart)
                            .width(275.dp),
                        style = TextStyle(fontSize = Constant.bigFont)
                    )
                }
            }
        }
    }
}