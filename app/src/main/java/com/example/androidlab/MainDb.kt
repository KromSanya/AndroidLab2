package com.example.androidlab

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Character::class, CharacterDetail::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: MainDb? = null

        fun getDB(context: Context): MainDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDb::class.java,
                    "MarvelDB.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}