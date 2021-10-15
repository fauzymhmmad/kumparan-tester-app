package com.test.kumparan.ui.main.detailpost

import com.google.inject.Inject
import com.test.kumparan.api.ApiServiceInterface
import com.test.kumparan.model.CommentModel
import com.test.kumparan.model.PostModel
import com.test.kumparan.model.UserModel
import io.reactivex.Observable

class DetailPostInteractor : DetailPostContract.Interactor {

    @Inject
    lateinit var api : ApiServiceInterface

    override fun getUser(id: Int): Observable<UserModel> {
        return api.getUser(id)
    }

    override fun getComment(id: Int): Observable<List<CommentModel>> {
        return api.getComment(id)
    }

    override fun getDetailPost(id: Int): Observable<PostModel> {
        return api.getPostDetail(id)
    }
}