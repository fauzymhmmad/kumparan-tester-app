package com.test.kumparan.ui.main.post

import com.google.inject.Inject
import com.test.kumparan.ui.base.BasePresenter
import com.test.kumparan.util.disposeBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostPresenter: BasePresenter<PostContract.View>(), PostContract.Presenter {
    @Inject
    lateinit var interactor: PostInteractor

    override fun getPostList() {
        interactor.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view?.showList(it) }
            .disposeBy(disposeBag)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        view?.initializeView()
    }
}