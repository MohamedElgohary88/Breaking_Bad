package com.example.breakingbad.repository

import androidx.lifecycle.LiveData
import com.example.breakingbad.db.CharacterDao
import com.example.breakingbad.model.BBCharacter
import com.example.breakingbad.service.BreakingBadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {

    // Get Character from Room Database Offline
    var characters :LiveData<List<BBCharacter>> = characterDao.findAllCharacters()

    // Get Character from Retrofit Database Online
    suspend fun refreshCharacter (){
        withContext(Dispatchers.IO){
          val characters =  BreakingBadService.BreakingBadNetwork.serviceApi.getCharacters()
            // Insert Character From Online Database to Offline Room Database
            characterDao.insertAllCharacters(characters)
        }

    }
}