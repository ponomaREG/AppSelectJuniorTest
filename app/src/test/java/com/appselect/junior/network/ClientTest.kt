package com.appselect.junior.network

import com.appselect.junior.common.RxImmediateSchedulerAbstract
import com.appselect.junior.mock.mockResponse
import com.appselect.junior.model.Response
import com.appselect.junior.network.service.ReviewService
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class ClientTest: RxImmediateSchedulerAbstract() {
    private lateinit var reviewService: ReviewService
    private lateinit var client: Client
    private val mockResponse = mockResponse()

    @Before
    fun setup(){
        reviewService = mock()
        client = Client(reviewService)
    }

    @Test
    fun testFetchMoviesSuccess(){
        whenever(reviewService.getMovies(0))
            .thenReturn(Observable.just(mockResponse))

        val mockOnSuccess: (Response) -> Unit = mock()
        val mockOnComplete: () -> Unit = mock()
        val mockOnError: (String) -> Unit = mock()

        client.fetchMovies(
            page = 0,
            onComplete = mockOnComplete,
            onError = mockOnError,
            onSuccess = mockOnSuccess
        )

        verify(mockOnSuccess, atLeastOnce()).invoke(mockResponse)
    }

    @Test
    fun testFetchMoviesError(){
        whenever(reviewService.getMovies(0))
            .thenReturn(Observable.error(Throwable("ERROR")))

        val mockOnSuccess: (Response) -> Unit = mock()
        val mockOnError: (String) -> Unit = mock()
        val mockOnComplete: () -> Unit = mock()

        client.fetchMovies(
            page = 0,
            onSuccess = mockOnSuccess,
            onError = mockOnError,
            onComplete = mockOnComplete
        )

        verifyZeroInteractions(mockOnSuccess, mockOnComplete)
        verify(mockOnError, atLeastOnce()).invoke("ERROR")
    }
}