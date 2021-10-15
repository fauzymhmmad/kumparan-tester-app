package com.test.kumparan.ui.base

interface IPresenter {
    fun attachView(view: BaseView)

    fun onViewCreated()
    fun onViewStarted()
    fun onViewResumed()
    fun onViewStopped()

    fun onViewDestroyed()
}