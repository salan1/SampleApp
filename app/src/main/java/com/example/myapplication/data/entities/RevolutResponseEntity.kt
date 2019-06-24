package com.example.myapplication.data.entities

import com.google.gson.annotations.SerializedName

data class RevolutResponseEntity(
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: MutableMap<String, Float>
)