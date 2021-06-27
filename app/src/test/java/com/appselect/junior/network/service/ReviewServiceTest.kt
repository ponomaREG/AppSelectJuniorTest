package com.appselect.junior.network.service

import com.appselect.junior.common.RxImmediateSchedulerAbstract
import com.appselect.junior.mock.mockResponse
import com.appselect.junior.model.Response
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.nio.charset.StandardCharsets
import org.junit.Assert.assertEquals

class ReviewServiceTest: RxImmediateSchedulerAbstract() {
    private lateinit var reviewService: ReviewService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        reviewService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create()
    }

    @Test
    fun testGetMovies(){
        makeMockResponse("/ReviewServiceResponse.json", emptyMap())
        val testObs: TestObserver<Response> = TestObserver.create()
        reviewService.getMovies(0)
            .subscribe(testObs)

        assertEquals(1,testObs.valueCount())

        testObs.assertNoErrors()
        testObs.assertComplete()

        val mockResponse = mockResponse()
        val response = testObs.values()[0]
        assertEquals(mockResponse,response)
    }

    private fun makeMockResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }
}