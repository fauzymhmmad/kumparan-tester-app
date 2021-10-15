package com.test.kumparan.ui.main.detailpost

import android.util.Log
import com.google.inject.Inject
import com.test.kumparan.ui.base.BasePresenter
import com.test.kumparan.util.disposeBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPostPresenter : BasePresenter<DetailPostContract.View>(), DetailPostContract.Presenter {

    @Inject
    lateinit var interactor: DetailPostInteractor

    override fun getUser(id: Int) {
        interactor.getUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({view?.getUser(it)}, { Log.e("Error", it.message.toString())}).disposeBy(disposeBag)
    }

    override fun getComment(id: Int) {
        interactor.getComment(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({view?.getComment(it)}, {Log.e("Error Comment", it.message.toString())}).disposeBy(disposeBag)
    }

    override fun getDetailPost(id: Int) {
        interactor.getDetailPost(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({view?.getDetailPost(it)}, {Log.e("Error Detail Post", it.message.toString())}).disposeBy(disposeBag)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        view?.initializeView()
    }
}