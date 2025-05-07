package com.moviles.vinilos.database

import androidx.room.Dao
import androidx.room.Query
import com.moviles.vinilos.models.Artist

@Dao
interface ArtistsDao {
    @Query("SELECT * FROM artists_table")
    fun getArtists():List<Artist>

    @Query("DELETE FROM artists_table")
    suspend fun deleteAll():Int
}