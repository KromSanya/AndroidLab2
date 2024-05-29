package com.example.androidlab


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab.Constant.APIKEY
import kotlinx.coroutines.launch

import com.example.androidlab.Constant.characterDetail
import com.example.androidlab.Constant.characters
import com.example.androidlab.Constant.hash

import com.example.androidlab.Constant.limit
import com.example.androidlab.Constant.offset
import com.example.androidlab.Constant.ts

import retrofit2.HttpException
import java.io.IOException


class MarvelViewModel : ViewModel() {
    private val api = RetrofitInstance.api

    private var isLoading = false

    fun fetchCharacters() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            try {
                val response = api.getCharacters(APIKEY, hash, ts, offset, limit)
                //  characters.addAll(response.data.results)
                response.data.results.forEach { result ->
                    if (!result.thumbnail.path.contains("image_not_available"))
                        characters.add(result)
                }
                offset += limit
            } catch (e: IOException) {
                error(e)
            } catch (e: HttpException) {
                error(e)
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchCharacterDetail(characterId: String) {
        viewModelScope.launch {
            try {
                val response = api.getCharacterDetails(characterId, APIKEY, hash, ts)
                characterDetail = response.data.results.firstOrNull()
            } catch (e: IOException) {
                error(e)
            } catch (e: HttpException) {
                error(e)
            }
        }
    }
}








