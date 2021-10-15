package com.test.kumparan.ui.main.post

import com.test.kumparan.model.PostModel
import com.test.kumparan.ui.base.BaseView
import com.test.kumparan.ui.base.IPresenter
import io.reactivex.Observable

class PostContract {
    interface View : BaseView {
        fun showList(list: List<PostModel>)
    }

    interface Presenter: IPresenter{
        fun getPostList()
    }

    interface Interactor{
        fun getPostList(): Observable<List<PostModel>>
    }
}