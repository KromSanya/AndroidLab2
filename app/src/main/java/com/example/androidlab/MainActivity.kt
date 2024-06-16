package com.example.androidlab

import android.os.Bundle
import android.util.Log
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
import com.example.androidlab.Constant.APIKEY
import com.example.androidlab.Constant.characters
import com.example.androidlab.Constant.hash
import com.example.androidlab.Constant.ts

/** Your public key  bcebef1a106eb00c9aa79ec1ae131cfb
 *  Your private key e82270d64d19c69d98c1b1ad0c7e4b7a505c8f0d
 *  developer.marvel.com
 *  md5(ts+privateKey+publicKey)
 */
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
fun FirstScreen(navController: NavController, viewModel: MarvelViewModel = MarvelViewModel()) {

    val state = rememberLazyListState()

    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo.lastOrNull() }
            .collect { lastVisibleItem ->
                if (lastVisibleItem == null || lastVisibleItem.index >= characters.size - 5) {
                    viewModel.fetchCharacters()
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
                Box(modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = screenHeight * 0.05f)
                    .fillMaxSize()
                    .clickable {
                        viewModel.fetchCharacterDetail(characters[index].id.toString())
                        navController.navigate(route = Constant.DetailScreen + "/$index")
                    }) {
                    Log.d("CharacterRow", "Image URL:" + characters[index].thumbnail.path + "." + characters[index].thumbnail.extension)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data((characters[index].thumbnail.path + "." + characters[index].thumbnail.extension).replace("http://", "https://"))
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
                        text = characters[index].name,
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