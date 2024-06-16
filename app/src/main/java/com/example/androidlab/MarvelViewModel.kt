package com.example.androidlab


import android.util.Log
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab.Constant.APIKEY
import com.example.androidlab.Constant._characterDetail
import com.example.androidlab.Constant._characters
import com.example.androidlab.Constant.hash
import com.example.androidlab.Constant.limit
import com.example.androidlab.Constant.offset
import com.example.androidlab.Constant.ts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class MarvelViewModel() : ViewModel() {
    private val api = RetrofitInstance.api
    private var isLoading = false


    fun fetchCharacters(database: MainDb) {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            try {
                val response = api.getCharacters(APIKEY, hash, ts, offset, limit)
                val roomCharacters =
                    response.data.results.filterNot { it.thumbnail.path.contains("image_not_available") }
                        .map { it.toRoomCharacter() }
                database.characterDao().insertAll(roomCharacters)
                offset += limit
                _characters.value = roomCharacters
            } catch (e: IOException) {
                Log.e("MarvelViewModel", "IOException: ${e.message}", e)
            } catch (e: HttpException) {
                Log.e("MarvelViewModel", "HttpException: ${e.message}", e)

            } finally {
                isLoading = false
            }
        }
    }

    fun fetchCharacterDetail(characterId: String, database: MainDb) {
        viewModelScope.launch {
            Log.d("CharacterDetailID", characterId)
            try {
                val response = api.getCharacterDetails(characterId, APIKEY, hash, ts)
                Log.d("FetchDetail", response.toString())
                response.data.results.firstOrNull()?.let { characterDetail ->
                    database.characterDao()
                        .insertCharacterDetail(characterDetail.toRoomCharacterDetail())
                    _characterDetail.value = characterDetail.toRoomCharacterDetail()
                } ?: Log.e("FetchDetail", "No character details found in the response")
            } catch (e: IOException) {
                Log.e("MarvelViewModel", "IOException: ${e.message}", e)
            } catch (e: HttpException) {
                Log.e("MarvelViewModel", "HttpException: ${e.message}", e)
            }
        }
    }

    fun loadCharactersFromDb(database: MainDb) {
        viewModelScope.launch {
            val charactersFromDb = database.characterDao().getAllCharacters().firstOrNull()
            if (charactersFromDb != null && charactersFromDb.isNotEmpty()) {
                _characters.value = charactersFromDb
            }
        }
    }

    fun loadCharacterDetailFromDb(id: Int, database: MainDb) {
        viewModelScope.launch {
            database.characterDao().getCharacterDetailById(id).collect { characterDetailFromDb ->
                _characterDetail.value = characterDetailFromDb
            }
        }
    }
}


fun Character.toRoomCharacter(): RoomCharacter {
    return RoomCharacter(
        id = this.id,
        name = this.name,
        thumbnailPath = this.thumbnail.path,
        thumbnailExtension = this.thumbnail.extension
    )
}

fun CharacterDetail.toRoomCharacterDetail(): RoomCharacterDetail {
    return RoomCharacterDetail(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnailPath = this.thumbnail.path,
        thumbnailExtension = this.thumbnail.extension
    )
}

