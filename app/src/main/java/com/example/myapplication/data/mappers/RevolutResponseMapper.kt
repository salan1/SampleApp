package com.example.myapplication.data.mappers

import com.example.myapplication.data.entities.RevolutResponseEntity
import com.example.myapplication.domain.models.CurrencyRateModel
import com.example.myapplication.domain.models.ExchangeRatesModel

object RevolutResponseMapper : BaseSourceMapper<RevolutResponseEntity, ExchangeRatesModel> {

    override fun transformDto(entity: RevolutResponseEntity): ExchangeRatesModel =
        ExchangeRatesModel(
            entity.base,
            ArrayList(entity.rates.map {
                CurrencyRateModel(it.key, it.value)
            })
        )

    override fun transformModel(model: ExchangeRatesModel): RevolutResponseEntity {
        TODO("not needed")
    }
}