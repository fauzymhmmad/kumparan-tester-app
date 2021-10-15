package com.test.kumparan.ui.main.post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.kumparan.R
import com.test.kumparan.model.PostModel
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list by lazy { mutableListOf<PostModel>() }
    private var listener : PostListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(LayoutInflater.from(context).inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PostViewHolder){
            holder.bind(list[position])
        }
        holder.itemView.setOnClickListener {
            listener?.onClick(list[position].id, list[position].userId)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addPost(post: List<PostModel>){
        this.list.clear()
        this.list.addAll(post)
        notifyDataSetChanged()
    }

    fun setListener(listener: PostListener){
        this.listener = listener
    }

    private class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(model: PostModel){
            itemView.tvTitle?.text = model.title
            itemView.tvDesc?.text = model.body
        }
    }

    interface PostListener{
        fun onClick(id: Int, userId: Int)
    }
}