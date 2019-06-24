package com.example.myapplication.utils.stubs

import com.example.myapplication.data.entities.RevolutResponseEntity
import com.example.myapplication.domain.models.CurrencyRateModel
import com.example.myapplication.domain.models.ExchangeRatesModel
import net.andreinc.mockneat.MockNeat

object ModelStubs {

    private val mock: MockNeat = MockNeat.threadLocal()

    fun revolutResponseEntityStub(): RevolutResponseEntity =
        RevolutResponseEntity(
            mock.currencies().code().get(),
            mock.strings().size(3)
                .mapVals(
                    mock.floats().range(1f, 1000f)
                        .list(mock.ints().range(10, 33)).get()
                ).get()
        )

    fun exchangeRateStub(base: String? = null): ExchangeRatesModel =
        ExchangeRatesModel(
            base ?: mock.currencies().code().get(),
            mock.ints().range(10, 33).get().let {
                val arraylist = arrayListOf<CurrencyRateModel>()
                for (i in 0 until it) {
                    arraylist.add(currencyRateModelStub())
                }
                arraylist
            }
        )

    fun currencyRateModelStub(): CurrencyRateModel =
        CurrencyRateModel(
            mock.strings().size(3).get(),
            mock.floats().range(1f, 1000f).get()
        )

}