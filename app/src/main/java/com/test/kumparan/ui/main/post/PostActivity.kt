package com.test.kumparan.ui.main.post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.kumparan.R
import com.test.kumparan.model.PostModel
import com.test.kumparan.ui.base.BaseActivity
import com.test.kumparan.ui.main.detailpost.DetailPostActivity
import com.test.kumparan.ui.main.post.adapter.PostAdapter
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity: BaseActivity<PostContract.Presenter>(), PostContract.View, PostAdapter.PostListener {

    private val adapter by lazy { PostAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
    }

    override fun initializeView() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Post List"
        rv_post.layoutManager = LinearLayoutManager(this)
        rv_post.adapter = adapter
        presenter.getPostList()
    }

    override fun showList(list: List<PostModel>) {
        adapter.addPost(list)
        adapter.setListener(this)
    }

    override fun onClick(id: Int, userId: Int) {
        val intent = Intent(this, DetailPostActivity::class.java)
        intent.putExtra(DetailPostActivity.USER_ID, userId)
        intent.putExtra(DetailPostActivity.POST_ID, id)
        Log.e("ids", userId.toString())
        Log.e("idss", id.toString())
        startActivity(intent)
    }
}