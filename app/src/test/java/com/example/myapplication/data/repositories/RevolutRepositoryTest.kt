package com.example.myapplication.data.repositories

import com.example.myapplication.data.repositories.datasources.RevolutDatasource
import com.example.myapplication.utils.stubs.ModelStubs.revolutResponseEntityStub
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class RevolutRepositoryTest {

    @Mock
    private lateinit var datasource: RevolutDatasource
    lateinit var revolutRepository: RevolutRepository

    @BeforeEach
    fun setUp() {
        revolutRepository = RevolutRepository(datasource)
    }


    @Test
    fun getLatestPrices() {
        val entity = revolutResponseEntityStub()
        whenever(datasource.getPrices(ArgumentMatchers.nullable(String::class.java)))
            .thenReturn(Single.just(entity))

        revolutRepository.getLatestPrices(entity.base).test()
            .assertNoErrors()
            .assertComplete()
            .assertValueCount(1)
            .assertValue {
                it.base == entity.base && it.prices.size == entity.rates.size
            }
    }

}