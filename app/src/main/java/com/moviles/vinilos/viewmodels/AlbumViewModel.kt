package com.moviles.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviles.vinilos.models.Album

class AlbumViewModel (application: Application) : AndroidViewModel(application) {
    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private val _isNetworkError = MutableLiveData<Boolean>(false)
    val isNetworkError: LiveData<Boolean>
        get() = _isNetworkError

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        // Simulate network call
        _albums.value = listOf(
            Album(1, "Album 1", "cover1.jpg", "2023-01-01", "Description 1", "Genre 1", "Label 1"),
            Album(2, "Album 2", "cover2.jpg", "2023-02-01", "Description 2", "Genre 2", "Label 2")
        )
    }

    fun onNetworkErrorShown() {
        _isNetworkError.value = true
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}