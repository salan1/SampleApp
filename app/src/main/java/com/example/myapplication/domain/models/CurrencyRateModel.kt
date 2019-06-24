package com.example.myapplication.domain.models

data class CurrencyRateModel(
    val currency: String,
    val rate: Float,
    var base: Boolean = false
) {
    var price: Float = 0f
}