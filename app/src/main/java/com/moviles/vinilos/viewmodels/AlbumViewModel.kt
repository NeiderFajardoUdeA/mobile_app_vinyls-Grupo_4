package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.repositories.AlbumRepository

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    //Lista completa
    private var allAlbums: List<Album> = emptyList()

    //LiveData filtrada que observa la UI
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    private val _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private val _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        albumsRepository.refreshData({ list ->
            //Guardamos lista completa y actualizamos LiveData
            allAlbums = list
            _albums.value = list
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            _eventNetworkError.value = true
        })
    }

    fun searchAlbums(query: String) {
        if (query.isBlank()) {
            //Si no hay texto en la busqueda, devuelve todo
            _albums.value = allAlbums
        } else {
            _albums.value = allAlbums.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

   fun getAlbumById(albumId: Int): Album? {
        return albums.value?.get(albumId)
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}