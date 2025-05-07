package com.moviles.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.moviles.vinilos.database.ArtistsDao
import com.moviles.vinilos.models.Artist
import com.moviles.vinilos.network.ArtistServiceAdapter

class ArtistRepository (val application: Application, private val artistsDao: ArtistsDao){
    suspend fun refreshData(): List<Artist>{
        val cached = artistsDao.getArtists()
        return cached.ifEmpty {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else ArtistServiceAdapter.getInstance(application).getArtists()
        }
    }
}