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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


import coil.compose.AsyncImage

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
            model = imageUrls[index],
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
                text = names[index],
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp),
                style = TextStyle(fontSize = 28.sp)
            )
            Text(
                text = heroesPhrase[index],
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp),
                style = TextStyle(fontSize = 28.sp)
            )
        }
        
    }
}
/** тест будет по стрым вопросам только один правильный вариант ответа, 30 штук, могут быть новые вопросы по командам
 *  Владислав
 */