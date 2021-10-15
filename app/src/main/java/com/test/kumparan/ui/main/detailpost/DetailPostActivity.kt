package com.test.kumparan.ui.main.detailpost

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.kumparan.R
import com.test.kumparan.model.CommentModel
import com.test.kumparan.model.PostModel
import com.test.kumparan.model.UserModel
import com.test.kumparan.ui.base.BaseActivity
import com.test.kumparan.ui.main.detailpost.adapter.CommentAdapter
import com.test.kumparan.ui.main.userdetail.UserDetailActivity
import com.test.kumparan.util.setUnderlineSpannableText
import kotlinx.android.synthetic.main.activity_detail_post.*

class DetailPostActivity: BaseActivity<DetailPostContract.Presenter>(), DetailPostContract.View {

    private val commentAdapter by lazy { CommentAdapter(this) }

    companion object{
        const val USER_ID = "userId"
        const val POST_ID = "postId"
    }

    private var postId = 0
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)
    }

    override fun initializeView() {
        postId = intent.getIntExtra(POST_ID, 0)
        userId = intent.getIntExtra(USER_ID, 0)
        presenter.getComment(postId)
        presenter.getDetailPost(postId)
        presenter.getUser(userId)
        setSupportActionBar(toolbarDetail)
        toolbarDetail.setNavigationIcon(R.drawable.ic_arrow_back)
        supportActionBar?.title = "Detail Post"
        rvComment.layoutManager = LinearLayoutManager(this)
        rvComment.adapter = commentAdapter
    }

    override fun getUser(userModel: UserModel) {
        tvUsername?.setUnderlineSpannableText(userModel.userName)
        tvUsername.setOnClickListener {
            Intent(this, UserDetailActivity::class.java).apply {
                putExtra(UserDetailActivity.USER_ID, userModel.id)
                startActivity(this)
            }
        }
    }

    override fun getComment(list: List<CommentModel>) {
        commentAdapter.addComment(list)
    }

    override fun getDetailPost(postModel: PostModel) {
        tvTitleDetail?.text = postModel.title
        tvDescDetail?.text = postModel.body
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}