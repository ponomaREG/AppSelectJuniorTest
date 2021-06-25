package com.appselect.junior.repository

import com.appselect.junior.model.Movie
import com.appselect.junior.network.Client
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val client: Client
) {
    fun fetchMovies(
        page: Int,
        onComplete:() -> Unit,
        onError:(String) -> Unit,
        onSuccess:(List<Movie>) -> Unit
    ){
        client.fetchMovies(
            page = page,
            onComplete = onComplete,
            onError = onError,
            onSuccess = {onSuccess(it.results)}
        )
    }
}