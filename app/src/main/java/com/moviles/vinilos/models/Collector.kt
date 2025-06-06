package com.moviles.vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collectors_table")
data class Collector (
    @PrimaryKey val collectorId : Int,
    val name : String,
    val telephone: String,
    val email: String,
    val photo: String = "https://www.pngall.com/wp-content/uploads/5/Avatar-Profile-PNG-Clipart.png"
)