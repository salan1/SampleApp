package com.example.myapplication.presentation.presenters.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.interactors.GetPricesUseCase
import com.example.myapplication.presentation.presenters.impl.MainViewModel

class MainFactory constructor(private val getPricesUseCase: GetPricesUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getPricesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}