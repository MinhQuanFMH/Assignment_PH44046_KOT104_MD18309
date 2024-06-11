package com.example.asmkot_ph44046.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Carts(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idProduct: Int,
    val nameProduct: String,
    val imgProduct: String,
    val priceProduct: Int,
    val email: String,
    var quantity: Int
)