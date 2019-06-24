package com.example.myapplication.data.api

import com.example.myapplication.data.entities.RevolutResponseEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutApi {

    @GET("latest")
    fun getLatestByBase(@Query("base") base: String?): Single<RevolutResponseEntity>

    @GET("latest")
    fun getLatest(): Single<RevolutResponseEntity>

}