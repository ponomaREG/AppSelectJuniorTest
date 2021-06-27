package com.appselect.junior.viewmodel

import com.appselect.junior.common.RxImmediateSchedulerAbstract
import com.appselect.junior.mock.mockMovieList
import com.appselect.junior.mock.mockResponse
import com.appselect.junior.model.Movie
import com.appselect.junior.network.Client
import com.appselect.junior.network.service.ReviewService
import com.appselect.junior.repository.MainRepository
import com.appselect.junior.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest: RxImmediateSchedulerAbstract() {
    private val reviewService:ReviewService = mock()
    private val client = Client(reviewService)
    private val repository: MainRepository = MainRepository(client)
    private val viewModel = MainViewModel(repository)
    private val mockResponse = mockResponse()

    @Test
    fun testLoadMoviesSuccess(){
        val obs = Observable.just(mockResponse)
        whenever(reviewService.getMovies(0))
            .thenReturn(obs)
        viewModel.movies.observeForever {
            assertEquals(it, mockResponse.results)
        }
        viewModel.loadMovies(0)
        verify(reviewService, atLeastOnce()).getMovies(0)
    }

    @Test
    fun testLoadMoviesError(){
        whenever(reviewService.getMovies(0))
            .thenReturn(Observable.error(Throwable("ERROR")))
        viewModel.toast.observeForever {
            assertEquals(it, "ERROR")
        }
        viewModel.loadMovies(0)
        verify(reviewService, atLeastOnce()).getMovies(0)
    }
}