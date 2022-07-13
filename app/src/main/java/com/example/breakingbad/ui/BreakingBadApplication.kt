package com.example.breakingbad.ui

import android.app.Application
import com.example.breakingbad.db.CharacterDatabase
import com.example.breakingbad.repository.CharacterRepository

class BreakingBadApplication:Application() {

    val database by lazy {
        CharacterDatabase.createDatabase(this)
    }
    val characterRepository by lazy {
        CharacterRepository(database.createCharacter())
    }
}