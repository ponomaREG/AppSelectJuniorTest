package com.appselect.junior.network

import com.appselect.junior.model.Response
import com.appselect.junior.network.service.ReviewService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            offset = page * 20
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                onSuccess,
                {onError(it.message.toString())},
                onComplete
            )
    }
}