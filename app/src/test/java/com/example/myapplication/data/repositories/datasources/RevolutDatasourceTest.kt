package com.example.myapplication.data.repositories.datasources

import com.example.myapplication.data.api.RevolutApi
import com.example.myapplication.utils.MockUtils.getJson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val FILE_MOCK_SUCCESS = "ResponseSuccess.json"

internal class RevolutDatasourceTest {

    private lateinit var server: MockWebServer
    private lateinit var datasource: RevolutDatasource

    @BeforeEach
    fun setUp() {
        server = MockWebServer()
        val okHttpClient = OkHttpClient.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val client = retrofit.create(RevolutApi::class.java)
        datasource = RevolutDatasource(client)
    }

    @AfterEach
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getPrices() {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getJson(FILE_MOCK_SUCCESS))
        )
        datasource.getPrices().test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertComplete()
            .assertValue {
                it.base == "EUR" && it.rates.isNotEmpty()
            }
    }

}