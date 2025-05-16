package com.moviles.vinilos.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moviles.vinilos.models.Album

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM albums_table")
    fun getAlbums():List<Album>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(album: Album)

    @Query("DELETE FROM albums_table")
    suspend fun deleteAll():Int

    @Query("DELETE FROM albums_table WHERE albumId = :albumId")
    suspend fun delete(albumId: Int): Int

    @Update
    suspend fun update(album: Album): Int
}