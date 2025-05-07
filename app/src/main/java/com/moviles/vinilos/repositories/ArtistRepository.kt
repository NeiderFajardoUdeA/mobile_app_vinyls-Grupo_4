package com.moviles.vinilos.repositories

import android.app.Application
import com.moviles.vinilos.models.Artist
import com.moviles.vinilos.network.ArtistServiceAdapter

class ArtistRepository (private val application: Application){
    suspend fun refreshData(): List<Artist> {
        return ArtistServiceAdapter.getInstance(application).getArtists()
    }
}