package com.test.kumparan.ui.main.photos

import android.util.Log
import com.google.inject.Inject
import com.test.kumparan.ui.base.BasePresenter
import com.test.kumparan.util.disposeBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotosPresenter: BasePresenter<PhotosContract.View>(), PhotosContract.Presenter {

    @Inject
    lateinit var interactor: PhotosInteractor

    override fun showPhoto(id: Int) {
        interactor.showPhoto(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({view?.showPhotos(it)}, { Log.e("Error Photos", it.message.toString())}).disposeBy(disposeBag)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        view?.initializeView()
    }
}