package com.moviles.vinilos.models
import androidx.room.*

@Entity(tableName = "albums_table")
data class Album (
    @PrimaryKey val albumId : Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
)
