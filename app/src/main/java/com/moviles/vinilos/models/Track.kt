package com.moviles.vinilos.models

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "tracks_table")
class Track (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val duration: String,
)