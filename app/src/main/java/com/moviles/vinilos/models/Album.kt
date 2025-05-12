package com.moviles.vinilos.models

<<<<<<< HEAD
import androidx.room.*

@Entity(tableName = "albums_table")
data class Album (
    @PrimaryKey val albumId : Int,
=======
data class Album (
    val albumId:Int,
>>>>>>> main
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
)
