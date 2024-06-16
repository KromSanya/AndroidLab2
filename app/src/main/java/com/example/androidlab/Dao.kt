package com.example.androidlab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<RoomCharacter>)

    @Query("SELECT * FROM RoomCharacter")
    fun getAllCharacters(): Flow<List<RoomCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterDetail(characterDetail: RoomCharacterDetail)

    @Query("SELECT * FROM RoomCharacterDetail WHERE id = :id")
    fun getCharacterDetailById(id: Int): Flow<RoomCharacterDetail>
}