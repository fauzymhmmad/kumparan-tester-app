package com.test.kumparan.ui.main.userdetail

import com.test.kumparan.model.AlbumModel
import com.test.kumparan.model.UserModel
import com.test.kumparan.ui.base.BaseView
import com.test.kumparan.ui.base.IPresenter
import io.reactivex.Observable

class UserDetailContract {
    interface View: BaseView{
        fun getUserDetail(user: UserModel)
        fun getListAlbums(list: List<AlbumModel>)
    }

    interface Presenter: IPresenter{
        fun getUser(id: Int)
        fun getAlbums(id: Int)
    }

    interface Interactor{
        fun getUser(id: Int): Observable<UserModel>
        fun getAlbums(id: Int): Observable<List<AlbumModel>>
    }
}