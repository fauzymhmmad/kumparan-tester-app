package com.test.kumparan.ui.main.photodetail

import android.os.Bundle
import coil.api.load
import com.test.kumparan.R
import com.test.kumparan.ui.base.BaseActivity
import com.test.kumparan.ui.base.IPresenter
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity: BaseActivity<IPresenter>() {

    companion object{
        const val PHOTOS = "photos"
        const val TITLE = "title"
    }

    private var photos = ""
    private var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        intent?.getStringExtra(PHOTOS)?.let { photos = it }
        intent?.getStringExtra(TITLE)?.let { title = it }
        initializeView()
    }

    private fun initializeView(){
        setSupportActionBar(toolbarPhotosDetail)
        supportActionBar?.title = title
        toolbarPhotosDetail?.setNavigationIcon(R.drawable.ic_arrow_back)

        photosDetail.load(photos){
            placeholder(R.color.warm_grey)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}