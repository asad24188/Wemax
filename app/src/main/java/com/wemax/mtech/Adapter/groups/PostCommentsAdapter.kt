package com.wemax.mtech.Adapter.groups

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Model.groups.GroupMembersModel
import com.wemax.mtech.Model.groups.PostCommentsModel
import com.wemax.mtech.R

class PostCommentsAdapter(val context: Context, val list: ArrayList<PostCommentsModel>) :
    RecyclerView.Adapter<PostCommentsAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImageInComment)
        val commentMessage = itemView.findViewById<TextView>(R.id.commentMessage)
        val timeComment = itemView.findViewById<TextView>(R.id.hoursTimeComment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment_layout, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userName.text = model.userName
        holder.commentMessage.text = model.userComment
        holder.timeComment.text = model.commentTime
        holder.userImage.setImageResource(model.userImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}