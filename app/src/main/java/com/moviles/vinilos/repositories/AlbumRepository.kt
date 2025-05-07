package com.moviles.vinilos.repositories

import android.app.Application
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.network.AlbumServiceAdapter

class AlbumRepository (private val application: Application){
    suspend fun refreshData(): List<Album> {
        return AlbumServiceAdapter.getInstance(application).getAlbums()
    }
}