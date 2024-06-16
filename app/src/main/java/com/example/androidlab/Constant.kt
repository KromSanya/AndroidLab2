package com.example.androidlab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigInteger
import java.security.MessageDigest

object Constant {


    const val APIKEY = "bcebef1a106eb00c9aa79ec1ae131cfb"
    const val PRIVATEKEY = "e82270d64d19c69d98c1b1ad0c7e4b7a505c8f0d"


    const val HomeScreen = "FirstScreen"
    const val DetailScreen = "SecondScreen"
    const val ChooseHero = "Choose your hero"

    val averFont = 28.sp
    val bigFont = 36.sp

    var _characters = MutableStateFlow<List<RoomCharacter>>(emptyList())
    var characters: StateFlow<List<RoomCharacter>> = _characters

    var _characterDetail = MutableStateFlow<RoomCharacterDetail?>(null)
    var characterDetail: StateFlow<RoomCharacterDetail?> = _characterDetail


    val md5 = MessageDigest.getInstance("MD5")
    val ts = System.currentTimeMillis().toString()
    val input = ts + PRIVATEKEY + APIKEY
    val hashByteArray = md5.digest(input.toByteArray())
    val hash = BigInteger(1, hashByteArray).toString(16).padStart(32, '0')

    var offset = 0
    var limit = 20
}