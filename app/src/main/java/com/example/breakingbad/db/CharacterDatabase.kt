package com.example.breakingbad.db

import android.content.Context
import androidx.room.*
import com.example.breakingbad.model.BBCharacter

@Database(entities = [BBCharacter::class],
    version = 1,
)

@TypeConverters(AppTypeConverters::class)
abstract class CharacterDatabase :RoomDatabase(){

    abstract fun createCharacter():CharacterDao

    companion object{
        private var INSTANCE : CharacterDatabase? = null

        fun createDatabase (context: Context):CharacterDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext
                    ,CharacterDatabase::class.java,
                    "bbapp",
                ).build()
                INSTANCE  = instance
                return instance
            }
        }
    }
}