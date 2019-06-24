package com.example.myapplication.data.repositories

import com.example.myapplication.data.mappers.RevolutResponseMapper
import com.example.myapplication.data.repositories.datasources.RevolutDatasource
import com.example.myapplication.domain.models.ExchangeRatesModel
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RevolutRepository @Inject constructor(private val revolutDatasource: RevolutDatasource) {

    fun getLatestPrices(base: String?): Single<ExchangeRatesModel> {
        return revolutDatasource.getPrices(base).map {
            RevolutResponseMapper.transformDto(it)
        }
    }

}