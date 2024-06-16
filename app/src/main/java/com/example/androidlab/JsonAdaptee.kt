package com.example.androidlab

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


data class CharacterResponse(
    @Json(name = "data") val data: CharacterData,
    @Json(name = "status") val status: String
)

data class CharacterData(
    @Json(name = "results") val results: List<Character>
)

data class Character(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
 //   @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)

data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)

data class CharacterDetailResponse(
    @Json(name = "data") val data: CharacterDetailData
)

data class CharacterDetailData(
    @Json(name = "results") val results: List<CharacterDetail>
)

data class CharacterDetail(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
)