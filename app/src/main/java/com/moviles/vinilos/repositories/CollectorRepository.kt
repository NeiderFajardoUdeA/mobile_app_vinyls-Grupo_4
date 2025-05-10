package com.moviles.vinilos.repositories

import android.content.Context
import android.app.Application
import com.moviles.vinilos.database.CollectorsDao
import com.moviles.vinilos.models.Collector
import com.moviles.vinilos.network.CollectorServiceAdapter
import android.net.ConnectivityManager

class CollectorRepository(val application: Application, private val collectorDao: CollectorsDao) {
    suspend fun refreshData(): List<Collector> {
        val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (cm.activeNetworkInfo?.isConnected == true) {
            try {
                //Obtener datos del servicio remoto
                val remoteCollectors = CollectorServiceAdapter.getInstance(application).getCollectors()

                //Almacenar en caché local (DAO)
                remoteCollectors.forEach { collector -> collectorDao.insert(collector) }

                //Devolver los datos frescos del servicio
                remoteCollectors
            } catch (e: Exception) {
                //Si el servicio falla, devolver caché local
                collectorDao.getAllCollectors() ?: emptyList()
            }
        } else {
            //Devolver solo caché local
            collectorDao.getAllCollectors() ?: emptyList()
        }
    }

}