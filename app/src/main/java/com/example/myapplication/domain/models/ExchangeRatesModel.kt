package com.example.myapplication.domain.models

class ExchangeRatesModel(
    val base: String,
    val prices: ArrayList<CurrencyRateModel>
)