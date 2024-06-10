package com.example.androidlab

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitInstance {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val api: MarvelApi by lazy {
        retrofit.create(MarvelApi::class.java)
    }
}


interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharacterResponse

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): CharacterDetailResponse
}


@Entity (tableName = "Response")
data class CharacterResponse(
    @Json(name = "data") val data: CharacterData,
    @Json(name = "status") val status: String
)
@Entity (tableName = "CharactersList")
data class CharacterData(
    @ColumnInfo(name = "Characters")
    @Json(name = "results") val results: List<Character>
)

@Entity (tableName = "Character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id") val id: Int,
    @ColumnInfo(name = "name")
    @Json(name = "name") val name: String,
    @ColumnInfo(name = "thumbnail")
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)
@Entity
data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)
@Entity
data class CharacterDetailResponse(
    @Json(name = "data") val data: CharacterDetailData
)
@Entity
data class CharacterDetailData(
    @Json(name = "results") val results: List<CharacterDetail>
)
@Entity (tableName = "CharactersDetails")
data class CharacterDetail(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id") val id: Int,
    @ColumnInfo(name = "name",)
    @Json(name = "name") val name: String,
    @ColumnInfo(name = "description")
    @Json(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail")
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
)