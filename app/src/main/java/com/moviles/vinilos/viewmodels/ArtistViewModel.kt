package com.moviles.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.moviles.vinilos.database.VinylRoomDatabase
import com.moviles.vinilos.models.Artist
import com.moviles.vinilos.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistViewModel(application: Application) : AndroidViewModel(application) {

    private val artistRepository = ArtistRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).artistsDao())

    //Lista completa
    private var allArtists: List<Artist> = emptyList()

    //LiveData filtrada que observa la UI
    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>>
        get() = _artists

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
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = artistRepository.refreshData()
                    allArtists = data
                    _artists.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun searchArtists(query: String) {
        if (query.isBlank()) {
            //Si no hay texto en la busqueda, devuelve todo
            _artists.value = allArtists
        } else {
            _artists.value = allArtists.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun getArtistById(artistId: Int): Artist? {
        return artists.value?.get(artistId)
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}