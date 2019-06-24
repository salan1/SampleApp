package com.example.myapplication.domain.interactors.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCaseObservable<T> {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun getObservable(params: Params): Observable<T>

    /**
     * Executes the current UseCase.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * with [.getObservable].
     */
    fun execute(params: Params, observer: DisposableObserver<T>) {
        val observable: Observable<T> = this.getObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(params: Params): Observable<T> {
        return this.getObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}