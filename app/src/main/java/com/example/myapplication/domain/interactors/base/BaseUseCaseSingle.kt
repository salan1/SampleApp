package com.example.myapplication.domain.interactors.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCaseSingle<T> {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun getSingle(params: Params): Single<T>

    fun execute(params: Params, observer: DisposableSingleObserver<T>) {
        val single: Single<T> = this.getSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        addDisposable(single.subscribeWith(observer))
    }

    fun execute(params: Params): Single<T> {
        return this.getSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

}