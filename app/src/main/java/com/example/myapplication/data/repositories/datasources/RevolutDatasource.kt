package com.example.myapplication.data.repositories.datasources

import com.example.myapplication.data.api.RevolutApi
import com.example.myapplication.data.entities.RevolutResponseEntity
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RevolutDatasource @Inject constructor(private val client: RevolutApi) {

    fun getPrices(base: String? = null): Single<RevolutResponseEntity> {
        return if (base == null) {
            client.getLatest()
        } else {
            client.getLatestByBase(base)
        }
    }

}