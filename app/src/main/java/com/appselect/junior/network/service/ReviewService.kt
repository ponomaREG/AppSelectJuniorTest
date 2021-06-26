package com.appselect.junior.network.service

import com.appselect.junior.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ReviewService {
    @GET("/svc/movies/v2/reviews/all.json")
    fun getMovies(
        @Query("offset") offset: Int,
        @Query("api-key") apiKey: String = "FbwuufLPDRjAlGUIQNAbrjuDxq3Why2a"
    ): Observable<Response>
}