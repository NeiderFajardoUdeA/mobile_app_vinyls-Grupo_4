package com.moviles.vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.network.AlbumServiceAdapter
import com.moviles.vinilos.database.AlbumsDao

class AlbumRepository (val application: Application, private val albumsDao: AlbumsDao){
    suspend fun refreshData(): List<Album>{
        val cached = albumsDao.getAlbums()
        return cached.ifEmpty {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else AlbumServiceAdapter.getInstance(application).getAlbums()
        }
    }
}