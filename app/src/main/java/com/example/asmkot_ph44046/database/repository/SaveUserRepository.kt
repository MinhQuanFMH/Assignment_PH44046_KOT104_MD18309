package com.example.asmkot_ph44046.database.repository

import androidx.lifecycle.LiveData
import com.example.asmkot_ph44046.database.dao.SaveUserDao
import com.example.asmkot_ph44046.database.entities.SaveUsers

class SaveUserRepository(private val saveUserDao: SaveUserDao) {
    suspend fun addUser(saveUsers: SaveUsers) {
        saveUserDao.addUser(saveUsers)
    }

    suspend fun deleteUser(saveUsers: SaveUsers) {
        saveUserDao.deleteUser(saveUsers)
    }

    val getUser: LiveData<SaveUsers> = saveUserDao.getUser()
}