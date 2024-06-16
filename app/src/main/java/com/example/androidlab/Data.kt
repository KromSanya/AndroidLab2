package com.example.androidlab

import com.squareup.moshi.Json

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