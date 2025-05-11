package com.moviles.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.network.AlbumServiceAdapter
import com.moviles.vinilos.database.AlbumsDao

class AlbumRepository (val application: Application, private val albumsDao: AlbumsDao){
    suspend fun refreshData(): List<Album> {
        val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (cm.activeNetworkInfo?.isConnected == true) {
            try {
                //Obtener datos del servicio remoto
                val remoteAlbums = AlbumServiceAdapter.getInstance(application).getAlbums()

                //Almacenar en caché local (DAO)
                remoteAlbums.forEach { album -> albumsDao.insert(album)}

                //Devolver los datos frescos del servicio
                remoteAlbums
            } catch (e: Exception) {
                //Si el servicio falla, devolver caché local
                albumsDao.getAlbums()
            }
        } else {
            //Devolver solo caché local
            albumsDao.getAlbums()
        }
    }
}