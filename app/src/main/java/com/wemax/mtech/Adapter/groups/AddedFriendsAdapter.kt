package com.wemax.mtech.Adapter.groups

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.R

class AddedFriendsAdapter(val context: Context, val list: ArrayList<AddedFriendsModel>) :
    RecyclerView.Adapter<AddedFriendsAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImageInComment)
        val cancelImage = itemView.findViewById<ImageView>(R.id.onlineImage)
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val onlineStatus = itemView.findViewById<TextView>(R.id.offlineStatus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_added_friend, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userImage.setImageResource(model.userImage)
        holder.userName.text = model.userName
        holder.cancelImage.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}