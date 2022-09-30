package com.wemax.mtech.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Activity.chat.ChatActivity
import com.wemax.mtech.Adapter.groups.PostCommentsAdapter
import com.wemax.mtech.Model.groups.PostCommentsModel
import com.wemax.mtech.R

class ChatAdapterr(val context: Context, val list: ArrayList<PostCommentsModel>) :
    RecyclerView.Adapter<ChatAdapterr.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.tv_requiestby)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImageInComment)
        val commentMessage = itemView.findViewById<TextView>(R.id.tv_descrip)
        val timeComment = itemView.findViewById<TextView>(R.id.tv_date)
        val itemlayout = itemView.findViewById<RelativeLayout>(R.id.chatbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
        return ChatAdapterr.myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userName.text = model.userName
        holder.commentMessage.text = model.userComment
        holder.timeComment.text = model.commentTime
        holder.userImage.setImageResource(model.userImage)

        holder.itemlayout.setOnClickListener {
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}