package com.example.asmkot_ph44046.database.repository

import androidx.lifecycle.LiveData
import com.example.asmkot_ph44046.database.dao.CartDao
import com.example.asmkot_ph44046.database.entities.Carts


class CartRepository(private val cartDao: CartDao) {
    suspend fun addToCart(carts: Carts) {
        cartDao.addToCart(carts)
    }

    suspend fun removeFromCart(carts: Carts) {
        cartDao.removeFromCart(carts)
    }

    suspend fun updateInCart(carts: Carts) {
        cartDao.updateInCart(carts)
    }

    fun getItemInCart(email: String): LiveData<List<Carts>> = cartDao.getItemInCart(email)
}