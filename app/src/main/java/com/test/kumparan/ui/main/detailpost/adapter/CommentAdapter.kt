package com.test.kumparan.ui.main.detailpost.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.kumparan.R
import com.test.kumparan.model.CommentModel
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val commentList by lazy { mutableListOf<CommentModel>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CommentViewHolder){
            holder.bind(commentList[position])
        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    fun addComment(list: List<CommentModel>){
        this.commentList.clear()
        this.commentList.addAll(list)
        notifyDataSetChanged()
    }

    private class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(model : CommentModel){
            itemView.tvUsernameComment?.text = model.name
            itemView.tvDescComment?.text = model.body
        }
    }
}