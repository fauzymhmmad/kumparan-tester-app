package com.test.kumparan.ui.main.photos

import com.test.kumparan.model.PhotoModel
import com.test.kumparan.ui.base.BaseView
import com.test.kumparan.ui.base.IPresenter
import io.reactivex.Observable

class PhotosContract {
    interface View: BaseView{
        fun showPhotos(list: List<PhotoModel>)
    }

    interface Presenter: IPresenter{
        fun showPhoto(id: Int)
    }

    interface Interactor{
        fun showPhoto(id: Int): Observable<List<PhotoModel>>
    }
}