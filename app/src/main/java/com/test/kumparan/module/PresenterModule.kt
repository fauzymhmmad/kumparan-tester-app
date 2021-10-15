package com.test.kumparan.module

import com.google.inject.AbstractModule
import com.test.kumparan.ui.base.BaseView
import com.test.kumparan.ui.base.IPresenter
import com.test.kumparan.ui.main.detailpost.DetailPostContract
import com.test.kumparan.ui.main.detailpost.DetailPostInteractor
import com.test.kumparan.ui.main.detailpost.DetailPostPresenter
import com.test.kumparan.ui.main.photos.PhotosContract
import com.test.kumparan.ui.main.photos.PhotosInteractor
import com.test.kumparan.ui.main.photos.PhotosPresenter
import com.test.kumparan.ui.main.post.PostContract
import com.test.kumparan.ui.main.post.PostInteractor
import com.test.kumparan.ui.main.post.PostPresenter
import com.test.kumparan.ui.main.userdetail.UserDetailContract
import com.test.kumparan.ui.main.userdetail.UserDetailInteractor
import com.test.kumparan.ui.main.userdetail.UserDetailPresenter

class PresenterModule : AbstractModule() {
    override fun configure() {
        val presenter = object : IPresenter {
            override fun attachView(view: BaseView) {}
            override fun onViewCreated() {}
            override fun onViewStarted() {}
            override fun onViewResumed() {}
            override fun onViewStopped() {}
            override fun onViewDestroyed() {}
        }
        bind(IPresenter::class.java).toInstance(presenter)
        configurePost()
        configureDetailPost()
        configureUserDetail()
        configurePhoto()
    }

    private fun configurePost(){
        bind(PostContract.Presenter::class.java).to(PostPresenter::class.java)
        bind(PostContract.Interactor::class.java).to(PostInteractor::class.java)
    }

    private fun configureDetailPost(){
        bind(DetailPostContract.Presenter::class.java).to(DetailPostPresenter::class.java)
        bind(DetailPostContract.Interactor::class.java).to(DetailPostInteractor::class.java)
    }

    private fun configureUserDetail(){
        bind(UserDetailContract.Presenter::class.java).to(UserDetailPresenter::class.java)
        bind(UserDetailContract.Interactor::class.java).to(UserDetailInteractor::class.java)
    }

    private fun configurePhoto(){
        bind(PhotosContract.Presenter::class.java).to(PhotosPresenter::class.java)
        bind(PhotosContract.Interactor::class.java).to(PhotosInteractor::class.java)
    }
}