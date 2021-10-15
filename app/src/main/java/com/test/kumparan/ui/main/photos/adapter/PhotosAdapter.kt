package com.test.kumparan.ui.main.photos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.test.kumparan.R
import com.test.kumparan.model.PhotoModel
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listPhotos by lazy { mutableListOf<PhotoModel>() }

    private var listener: PhotoListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PhotosViewHolder){
            holder.bind(listPhotos[position])
            holder.itemView.setOnClickListener {
                listener?.onClick(listPhotos[position].url, listPhotos[position].title)
            }
        }
    }

    fun addPhotos(list: List<PhotoModel>){
        this.listPhotos.clear()
        this.listPhotos.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: PhotoListener){
        this.listener = listener
    }

    override fun getItemCount(): Int = listPhotos.size

    private class PhotosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(model: PhotoModel){
            itemView.images.load(model.thumbnailUrl){
                placeholder(R.color.warm_grey)
            }
        }
    }

    interface PhotoListener{
        fun onClick(photo: String, title: String)
    }
}