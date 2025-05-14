package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moviles.vinilos.database.VinylRoomDatabase
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import org.json.JSONObject

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).albumsDao())

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

    private val _albumCreated = MutableLiveData<Boolean>()
    val albumCreated: LiveData<Boolean> = _albumCreated

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = albumsRepository.refreshData()
                    allAlbums = data
                    _albums.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
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

    fun createAlbum(postParams: Map<String, String>) {
        viewModelScope.launch {
            try {
                val json = JSONObject(postParams)
                val result = albumsRepository.createAlbum(json)
                Log.d("crear", "res: $result")
                _albumCreated.postValue(true)
            } catch (e: Exception) {
                _albumCreated.postValue(false)
            }
        }
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