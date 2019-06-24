package com.example.myapplication.presentation.presenters

import androidx.lifecycle.ViewModel
import com.example.myapplication.presentation.ui.base.BaseView
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel<T : BaseView> : ViewModel() {

    protected val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

}