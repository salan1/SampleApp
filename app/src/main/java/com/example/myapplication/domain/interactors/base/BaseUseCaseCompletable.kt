package com.example.myapplication.domain.interactors.base

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCaseCompletable {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun getCompletable(params: Params): Completable

    fun execute(params: Params, observer: DisposableCompletableObserver) {
        val completable: Completable = this.getCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        addDisposable(completable.subscribeWith(observer))
    }

    private fun addDisposable(disposableCompletableObserver: DisposableCompletableObserver) {
        disposables.add(disposableCompletableObserver)
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

}