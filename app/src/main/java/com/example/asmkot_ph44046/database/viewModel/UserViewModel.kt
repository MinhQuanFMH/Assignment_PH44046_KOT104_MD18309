package com.example.asmkot_ph44046.database.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asmkot_ph44046.database.entities.Users
import com.example.asmkot_ph44046.database.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun userSignIn(email: String, password: String)
            : LiveData<List<Users>> =
        userRepository.userSignIn(email, password)

    fun userSignUp(users: Users) {
        viewModelScope.launch {
            userRepository.userSignUp(users)
        }
    }

    fun getUser(email: String): LiveData<Users> = userRepository.getUser(email)
}