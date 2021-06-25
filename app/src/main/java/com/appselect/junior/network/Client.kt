package com.appselect.junior.network

import com.appselect.junior.model.Response
import com.appselect.junior.network.service.ReviewService
import javax.inject.Inject

class Client @Inject constructor(
    private val reviewService: ReviewService
) {
    fun fetchMovies(
        page: Int,
        onComplete:() -> Unit,
        onError:(String) -> Unit,
        onSuccess:(Response) -> Unit
    ){
        reviewService.getMovies(
            page = page
        )
    }
}