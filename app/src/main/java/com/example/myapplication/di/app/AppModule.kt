package com.example.myapplication.di.app

import android.app.Application
import android.content.Context
import com.example.myapplication.data.api.RevolutApi
import com.example.myapplication.data.api.RevolutClient
import com.example.myapplication.data.repositories.RevolutRepository
import com.example.myapplication.data.repositories.datasources.RevolutDatasource

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.baseContext

    // Clients
    @Provides
    @Singleton
    internal fun provideRevolutClient(): RevolutApi =
        RevolutClient().revolutApi


    // Datasources
    @Provides
    @Singleton
    internal fun provideRevolutDatasource(
        client: RevolutApi
    ): RevolutDatasource = RevolutDatasource(client)


    // Repos
    @Provides
    @Singleton
    internal fun provideRevolutRepository(
        datasource: RevolutDatasource
    ) = RevolutRepository(datasource)


}
