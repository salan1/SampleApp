package com.example.myapplication.presentation.ui.base

import android.os.Bundle
import dagger.android.support.DaggerFragment
import timber.log.Timber

abstract class BaseFragment : DaggerFragment(), BaseView {


    val fragmentActivity: androidx.fragment.app.FragmentActivity?
        get() = activity

    val fragment: androidx.fragment.app.Fragment
        get() = this

    abstract fun getArgs(_bundle: Bundle)

    override fun onDetach() {
        Timber.i("Detaching fragment")
        super.onDetach()
    }

    override fun onDestroy() {
        Timber.i("Destroying fragment")
        super.onDestroy()
    }

}