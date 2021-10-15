package com.test.kumparan.ui.main.userdetail

import com.google.inject.Inject
import com.test.kumparan.api.ApiServiceInterface
import com.test.kumparan.model.AlbumModel
import com.test.kumparan.model.UserModel
import io.reactivex.Observable

class UserDetailInteractor: UserDetailContract.Interactor {

    @Inject
    lateinit var api: ApiServiceInterface

    override fun getUser(id: Int): Observable<UserModel> {
        return api.getUser(id)
    }

    override fun getAlbums(id: Int): Observable<List<AlbumModel>> {
        return api.getUserAlbums(id)
    }
}