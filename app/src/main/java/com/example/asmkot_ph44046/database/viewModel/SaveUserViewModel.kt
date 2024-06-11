package com.example.asmkot_ph44046.database.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asmkot_ph44046.database.entities.SaveUsers
import com.example.asmkot_ph44046.database.repository.SaveUserRepository
import kotlinx.coroutines.launch

class SaveUserViewModel(private val saveUserRepository: SaveUserRepository) : ViewModel() {
    fun addUser(saveUsers: SaveUsers) {
        viewModelScope.launch {
            saveUserRepository.addUser(saveUsers)
        }
    }

    fun deleteUser(saveUsers: SaveUsers) {
        viewModelScope.launch {
            saveUserRepository.deleteUser(saveUsers)
        }
    }

    val getUser: LiveData<SaveUsers> = saveUserRepository.getUser
}