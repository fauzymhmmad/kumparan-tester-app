package com.test.kumparan.ui.main.userdetail

import android.util.Log
import com.google.inject.Inject
import com.test.kumparan.ui.base.BasePresenter
import com.test.kumparan.util.disposeBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserDetailPresenter : BasePresenter<UserDetailContract.View>(), UserDetailContract.Presenter {

    @Inject
    lateinit var interactor: UserDetailInteractor

    override fun getUser(id: Int) {
        interactor.getUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view?.getUserDetail(it) }, { Log.e("Error User", it.message.toString()) })
            .disposeBy(disposeBag)
    }

    override fun getAlbums(id: Int) {
        interactor.getAlbums(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view?.getListAlbums(it) },
                { Log.e("Error Albums", it.message.toString()) }).disposeBy(disposeBag)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        view?.initializeView()
    }
}