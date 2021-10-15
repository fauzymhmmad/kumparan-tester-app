package com.test.kumparan.ui.main.photos

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.kumparan.R
import com.test.kumparan.model.PhotoModel
import com.test.kumparan.ui.base.BaseActivity
import com.test.kumparan.ui.main.photodetail.PhotoDetailActivity
import com.test.kumparan.ui.main.photos.adapter.PhotosAdapter
import com.test.kumparan.util.ItemDecoration
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : BaseActivity<PhotosContract.Presenter>(), PhotosContract.View, PhotosAdapter.PhotoListener{

    private val photosAdapter by lazy { PhotosAdapter(this) }


    companion object{
        const val PHOTO_ID = "photoId"
    }

    private var photoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
    }

    override fun initializeView() {
        photoId = intent.getIntExtra(PHOTO_ID, 0)
        presenter.showPhoto(photoId)
        setSupportActionBar(toolbarPhotos)
        supportActionBar?.title = "Photos"
        toolbarPhotos?.setNavigationIcon(R.drawable.ic_arrow_back)
        val item = ItemDecoration(8)
        rv_photo.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_photo.adapter = photosAdapter
        rv_photo.addItemDecoration(item)
    }

    override fun showPhotos(list: List<PhotoModel>) {
        photosAdapter.addPhotos(list)
        photosAdapter.setListener(this)
    }

    override fun onClick(photo: String, title: String) {
        Intent(this, PhotoDetailActivity::class.java).apply {
            putExtra(PhotoDetailActivity.PHOTOS, photo)
            putExtra(PhotoDetailActivity.TITLE, title)
            startActivity(this)
        }
    }
}