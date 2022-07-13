package com.example.breakingbad.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.repository.CharacterRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CharacterListViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    val character = characterRepository.characters
    fun refreshDataFromRepository() {
        viewModelScope.launch {
            characterRepository.refreshCharacter()
        }
    }

    class CharacterListViewModelFactory(private val characterRepository: CharacterRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom((CharacterListViewModel::class.java))){
                return CharacterListViewModel(characterRepository) as T

            }
            throw IllegalArgumentException("Unknown view Model Class")
        }
    }
}