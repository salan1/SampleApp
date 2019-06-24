package com.example.myapplication.di

import com.example.myapplication.data.repositories.RevolutRepository
import com.example.myapplication.di.scope.ActivityScope
import com.example.myapplication.domain.interactors.GetPricesUseCase
import com.example.myapplication.presentation.presenters.factories.MainFactory
import dagger.Module
import dagger.Provides


@Module
class MainModule {

    // ViewModel Factories
    @ActivityScope
    @Provides
    internal fun provideMainFactory(
        getPricesUseCase: GetPricesUseCase
    ): MainFactory = MainFactory(getPricesUseCase)

    // Use cases
    @ActivityScope
    @Provides
    internal fun provideGetPricesUseCase(
        revolutRepository: RevolutRepository
    ): GetPricesUseCase = GetPricesUseCase(revolutRepository)

}