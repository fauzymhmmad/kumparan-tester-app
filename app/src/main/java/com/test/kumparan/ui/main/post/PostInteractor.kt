package com.test.kumparan.ui.main.post

import com.google.inject.Inject
import com.test.kumparan.api.ApiServiceInterface
import com.test.kumparan.model.PostModel
import io.reactivex.Observable

class PostInteractor: PostContract.Interactor {
    @Inject
    lateinit var api: ApiServiceInterface

    override fun getPostList(): Observable<List<PostModel>> {
        return api.getPosts()
    }
}