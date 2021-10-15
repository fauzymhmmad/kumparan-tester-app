package com.test.kumparan.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter <T: BaseView>: IPresenter{
    val disposeBag: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @JvmField
    var view : T? = null

    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: BaseView) {
        this.view = view as T
    }

    fun addToDisposable(disposable: Disposable) {
        disposeBag.add(disposable)
    }

    override fun onViewCreated() {}
    override fun onViewStarted() {}
    override fun onViewResumed() {}
    override fun onViewStopped() {}

    override fun onViewDestroyed() {
        if (disposeBag.isDisposed) {
            disposeBag.dispose()
        }
    }
}
