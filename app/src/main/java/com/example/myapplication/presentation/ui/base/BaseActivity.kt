package com.example.myapplication.presentation.ui.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber


abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("onCreate")
        super.onCreate(savedInstanceState)
    }

    // Detach view from presenter on activity destroy
    override fun onDestroy() {
        Timber.v("onDestroy activity")
        super.onDestroy()
    }
}