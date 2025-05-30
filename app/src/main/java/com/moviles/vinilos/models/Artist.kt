package com.moviles.vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists_table")
data class Artist (
    @PrimaryKey val artistId: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)