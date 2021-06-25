package com.appselect.junior.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appselect.junior.model.Movie
import com.appselect.junior.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    private val _toast: MutableLiveData<String> = MutableLiveData()
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val toast: LiveData<String> = _toast
    val movies: LiveData<List<Movie>> = _movies
    val isLoading: ObservableBoolean = ObservableBoolean(false)

    fun loadMovies(page: Int){
        isLoading.set(true)
        repository.fetchMovies(
            page = page,
            onError = { errorString ->
                _toast.value = errorString
            },
            onSuccess = { moviesFromNetwork ->
                _movies.value = moviesFromNetwork
            },
            onComplete = {
                isLoading.set(false)
            }
        )
    }
}