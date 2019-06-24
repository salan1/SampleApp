package com.example.myapplication.presentation.presenters.impl

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.interactors.GetPricesUseCase
import com.example.myapplication.domain.interactors.GetPricesUseCase.Companion.PARAM_KEY_BASE
import com.example.myapplication.domain.interactors.GetPricesUseCase.Companion.PARAM_KEY_PRICE
import com.example.myapplication.domain.interactors.base.Params
import com.example.myapplication.domain.models.ExchangeRatesModel
import com.example.myapplication.domain.models.Result
import com.example.myapplication.presentation.presenters.BaseViewModel
import com.example.myapplication.presentation.ui.MainActivity
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MainViewModel(private val getPricesUseCase: GetPricesUseCase) : BaseViewModel<MainActivity>() {

    var disposable: Disposable? = null
    val ratesLiveData = MutableLiveData<Result<ExchangeRatesModel>>()

    init {
        ratesLiveData.postValue(Result.Loading)
    }

    fun getPrices(base: String? = null, price: Float? = null) {
        disposable?.also { it.dispose() }
        val params = Params.create()
        base?.also {
            params.putString(PARAM_KEY_BASE, it)
        }
        price?.also {
            params.putFloat(PARAM_KEY_PRICE, it)
        }
        disposable = getPricesUseCase.execute(params)
                .subscribeBy(
                        onNext = {
                            ratesLiveData.postValue(it)
                        },
                        onError = {
                            Timber.d(it)
                            ratesLiveData.postValue(Result.Error(it))
                        }
                )
    }

}