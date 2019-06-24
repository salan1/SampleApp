package com.example.myapplication.domain.interactors

import com.example.myapplication.data.repositories.RevolutRepository
import com.example.myapplication.domain.interactors.base.BaseUseCaseObservable
import com.example.myapplication.domain.interactors.base.Params
import com.example.myapplication.domain.models.CurrencyRateModel
import com.example.myapplication.domain.models.ExchangeRatesModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.example.myapplication.domain.models.Result
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GetPricesUseCase @Inject constructor(private val revolutRepository: RevolutRepository) :
    BaseUseCaseObservable<Result<ExchangeRatesModel>>() {

    override fun getObservable(params: Params): Observable<Result<ExchangeRatesModel>> {
        val price = params.getFloat(PARAM_KEY_PRICE, null)
        val base = params.getString(PARAM_KEY_BASE, null)
        return revolutRepository.getLatestPrices(base)
            .doOnSuccess {
                val count = price ?: 100f
                for (cur in it.prices) {
                    cur.price = count * cur.rate
                }
                val baseCur = CurrencyRateModel(it.base, 1f)
                baseCur.price = price ?: 100f
                baseCur.base = true
                val temp = it.prices[0]
                it.prices[0] = baseCur
                it.prices.add(temp)
                Timber.i("Base is ${it.prices[0].currency}")
            }
            .subscribeOn(Schedulers.computation())
            .map<Result<ExchangeRatesModel>> {
                Result.Success(it)
            }
            .toObservable()
            .repeatWhen {
                it.delay(1, TimeUnit.SECONDS)
            }
    }

    companion object {
        const val PARAM_KEY_BASE = "param_base"
        const val PARAM_KEY_PRICE = "param_price"
    }
}