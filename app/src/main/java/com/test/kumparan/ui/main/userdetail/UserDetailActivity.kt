package com.test.kumparan.ui.main.userdetail

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.kumparan.R
import com.test.kumparan.model.AlbumModel
import com.test.kumparan.model.UserModel
import com.test.kumparan.ui.base.BaseActivity
import com.test.kumparan.ui.main.photos.PhotosActivity
import com.test.kumparan.ui.main.userdetail.adapter.AlbumAdapter
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity: BaseActivity<UserDetailContract.Presenter>(), UserDetailContract.View, AlbumAdapter.AlbumListener {

    private val albumAdapter by lazy { AlbumAdapter(this) }

    companion object{
        const val USER_ID = "userId"
    }

    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
    }

    override fun initializeView() {
        id = intent.getIntExtra(USER_ID, 0)
        presenter.getUser(id)
        presenter.getAlbums(id)
        setSupportActionBar(toolbarUserDetail)
        supportActionBar?.title = "User Profile"
        toolbarUserDetail?.setNavigationIcon(R.drawable.ic_arrow_back)
        rv_album.layoutManager = LinearLayoutManager(this)
        rv_album.adapter = albumAdapter
    }

    override fun getUserDetail(user: UserModel) {
        tvUserNameDetail?.text = user.userName
        tvUserEmail?.text = user.email
        tvUserAddress?.text = user.address?.street
        tvUserCompany?.text = user.company?.name
    }

    override fun getListAlbums(list: List<AlbumModel>) {
        albumAdapter.addAlbums(list)
        albumAdapter.setListener(this)
    }

    override fun onClick(id: Int) {
        Intent(this, PhotosActivity::class.java).apply {
            putExtra(PhotosActivity.PHOTO_ID, id)
            startActivity(this)
        }
    }
}