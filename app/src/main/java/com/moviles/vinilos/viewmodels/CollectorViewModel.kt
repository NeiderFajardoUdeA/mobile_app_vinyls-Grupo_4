package com.moviles.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.moviles.vinilos.models.Collector
import com.moviles.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import com.moviles.vinilos.database.VinylRoomDatabase

class CollectorViewModel(application: Application) : AndroidViewModel(application) {
    private val collectorRepository = CollectorRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).collectorsDao())

    //Lista completa
    private var allCollectors: List<Collector> = emptyList()

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: MutableLiveData<List<Collector>>
        get() = _collectors

    private val _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: MutableLiveData<Boolean>
        get() = _eventNetworkError

    private val _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: MutableLiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try{
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = collectorRepository.refreshData()
                    allCollectors = data
                    _collectors.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }catch (e:Exception){
            _eventNetworkError.value = true
        }

    }

    fun searchCollectors(query: String) {
        if (query.isBlank()) {
            //Si no hay texto en la busqueda, devuelve todo
            _collectors.value = allCollectors
        } else {
            _collectors.value = allCollectors.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}