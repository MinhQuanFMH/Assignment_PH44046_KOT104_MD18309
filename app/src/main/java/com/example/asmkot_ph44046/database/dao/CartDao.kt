package com.example.asmkot_ph44046.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.asmkot_ph44046.database.entities.Carts


@Dao
interface CartDao {
    @Insert
    suspend fun addToCart(carts: Carts)

    @Delete
    suspend fun removeFromCart(carts: Carts)

    @Update
    suspend fun updateInCart(carts: Carts)

    @Query("select * from Carts where email = :email")
    fun getItemInCart(email: String): LiveData<List<Carts>>
}