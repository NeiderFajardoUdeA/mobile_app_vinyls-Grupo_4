package com.moviles.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.moviles.vinilos.database.ArtistsDao
import com.moviles.vinilos.models.Artist
import com.moviles.vinilos.network.ArtistServiceAdapter

class ArtistRepository (val application: Application, private val artistsDao: ArtistsDao){
    suspend fun refreshData(): List<Artist> {
        val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (cm.activeNetworkInfo?.isConnected == true) {
            try {
                //Obtener datos del servicio remoto
                val remoteArtists = ArtistServiceAdapter.getInstance(application).getArtists()

                //Almacenar en caché local (DAO)
                remoteArtists.forEach { artist -> artistsDao.insert(artist)}

                //Devolver los datos frescos del servicio
                remoteArtists
            } catch (e: Exception) {
                //Si el servicio falla, devolver caché local
                artistsDao.getArtists()
            }
        } else {
            //Devolver solo caché local
            artistsDao.getArtists()
        }
    }
}