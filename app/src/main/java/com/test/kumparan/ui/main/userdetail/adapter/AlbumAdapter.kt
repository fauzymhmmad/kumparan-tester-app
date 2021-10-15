package com.test.kumparan.ui.main.userdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.kumparan.R
import com.test.kumparan.model.AlbumModel
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listAlbums by lazy { mutableListOf<AlbumModel>() }

    private var albumListener: AlbumListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AlbumViewHolder){
            holder.bind(listAlbums[position])

            holder.itemView.setOnClickListener {
                albumListener?.onClick(listAlbums[position].id)
            }
        }
    }

    override fun getItemCount(): Int = listAlbums.size

    fun addAlbums(list: List<AlbumModel>){
        this.listAlbums.clear()
        this.listAlbums.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: AlbumListener){
        this.albumListener = listener
    }

    private class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       fun bind(model: AlbumModel){
           itemView.tvAlbumName?.text = model.title
       }
    }

    interface AlbumListener{
        fun onClick(id: Int)
    }
}