package com.moviles.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.moviles.vinilos.models.Artist
import com.moviles.vinilos.network.ArtistServiceAdapter

class ArtistRepository (private val application: Application){
    fun refreshData(callback: (List<Artist>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        ArtistServiceAdapter.getInstance(application).getArtists({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}