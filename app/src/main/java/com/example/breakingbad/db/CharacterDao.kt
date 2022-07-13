package com.example.breakingbad.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbad.model.BBCharacter

@Dao
interface CharacterDao {

    @Query("SELECT * from character")
    fun findAllCharacters(): LiveData<List<BBCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characterList: List<BBCharacter>)

}