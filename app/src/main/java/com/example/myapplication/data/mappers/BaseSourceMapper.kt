package com.example.myapplication.data.mappers

interface BaseSourceMapper<T, F> {
    fun transformDto(entity: T): F
    fun transformModel(model: F): T
}