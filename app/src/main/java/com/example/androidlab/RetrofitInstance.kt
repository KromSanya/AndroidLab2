package com.example.androidlab

import com.example.androidlab.Constant.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitInstance {

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




