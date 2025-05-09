package com.moviles.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.network.ServiceAdapter

class AlbumRepository (private val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        ServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}