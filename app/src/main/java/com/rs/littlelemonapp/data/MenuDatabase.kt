package com.rs.littlelemonapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuDatabase (
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String
)