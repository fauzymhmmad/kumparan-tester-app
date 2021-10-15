package com.test.kumparan.ui.main.detailpost

import com.test.kumparan.model.CommentModel
import com.test.kumparan.model.PostModel
import com.test.kumparan.model.UserModel
import com.test.kumparan.ui.base.BaseView
import com.test.kumparan.ui.base.IPresenter
import io.reactivex.Observable

class DetailPostContract {
    interface View : BaseView{
        fun getUser(userModel: UserModel)
        fun getComment(list: List<CommentModel>)
        fun getDetailPost(postModel: PostModel)
    }

    interface Presenter: IPresenter{
        fun getUser(id: Int)
        fun getComment(id: Int)
        fun getDetailPost(id: Int)
    }

    interface Interactor{
        fun getUser(id: Int) : Observable<UserModel>
        fun getComment(id: Int): Observable<List<CommentModel>>
        fun getDetailPost(id: Int) : Observable<PostModel>
    }
}