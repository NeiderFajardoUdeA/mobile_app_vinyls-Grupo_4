package com.moviles.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviles.vinilos.models.Collector
import com.moviles.vinilos.repositories.CollectorRepository

class CollectorViewModel(application: Application) : AndroidViewModel(application) {
    private val collectorRepository = CollectorRepository(application)

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
        collectorRepository.refreshData({ list ->
            //Guardar lista completa y actualizar LiveData
            allCollectors = list
            _collectors.value = list
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            _eventNetworkError.value = true
        })
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