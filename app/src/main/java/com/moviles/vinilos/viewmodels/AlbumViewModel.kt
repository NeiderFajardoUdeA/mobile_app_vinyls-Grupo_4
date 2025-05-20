package com.moviles.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.moviles.vinilos.database.VinylRoomDatabase
import com.moviles.vinilos.models.Album
import com.moviles.vinilos.models.Track
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

    private val _albumCreated = MutableLiveData<Boolean?>()
    val albumCreated: MutableLiveData<Boolean?> = _albumCreated

    init {
        refreshDataFromNetwork()
    }

    fun refreshDataFromNetwork() {
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
                albumsRepository.createAlbum(json)
                _albumCreated.postValue(true)
            } catch (e: Exception) {
                _albumCreated.postValue(false)
            }
        }
    }

    fun resetAlbumCreated() {
        _albumCreated.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    // Function to add a new track to an album
    fun addTrackToAlbum(albumId: Int, trackName: String, trackDuration: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val album = _albums.value?.find { it.albumId == albumId }
            if (album != null) {
                val newTrack = Track(name = trackName, duration = trackDuration)
                album.tracks.add(newTrack)
                albumsRepository.addTrackToAlbum(albumId, newTrack)
            }
        }
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