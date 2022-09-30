package com.wemax.mtech.Adapter.home.serviceDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.home.GalleryPhotoModel
import com.wemax.mtech.R

class GalleryAdapter(
    private val context: Context, private var photoList: List<GalleryPhotoModel>
) : RecyclerView.Adapter<GalleryAdapter.myViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_gallery, parent, false)
        return myViewModel(view)
    }

    override fun onBindViewHolder(holder: myViewModel, position: Int) {
        val photoList = photoList[position]
        holder.ivAvengers.setImageResource(photoList.image)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    class myViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivAvengers = itemView.findViewById<ImageView>(R.id.ivphoto)
    }
}