package com.test.kumparan.ui.main.photos

import com.google.inject.Inject
import com.test.kumparan.api.ApiServiceInterface
import com.test.kumparan.model.PhotoModel
import io.reactivex.Observable

class PhotosInteractor: PhotosContract.Interactor {

    @Inject
    lateinit var api: ApiServiceInterface

    override fun showPhoto(id: Int): Observable<List<PhotoModel>> {
        return api.getAlbumPhotos(id)
    }
}