package com.example.asmkot_ph44046.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.asmkot_ph44046.database.entities.Products


@Dao
interface ProductDao {
    @Query("select * from Products")
    fun getAllProduct(): LiveData<List<Products>>

    @Insert
    suspend fun addProduct(products: Products)

    @Query("select * from Products where id= :id")
    fun getItemProduct(id: Int): LiveData<Products>
}