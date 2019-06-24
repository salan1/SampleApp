package com.example.myapplication

import dagger.android.DaggerApplication
import timber.log.Timber

class App: DaggerApplication() {

    private val appComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector() = appComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

}