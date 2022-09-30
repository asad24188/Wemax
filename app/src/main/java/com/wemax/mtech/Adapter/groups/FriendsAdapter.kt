package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Adapter.groups.GroupMembersAdapter
import com.wemax.mtech.Model.groups.GroupMembersModel
import com.wemax.mtech.R

class FriendsAdapter(val context: Context, val list: ArrayList<GroupMembersModel>) :
    RecyclerView.Adapter<FriendsAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImage)
        val userRole = itemView.findViewById<TextView>(R.id.userRole)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_group_member, parent, false)
        return FriendsAdapter.myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userName.text = model.userName
        holder.userRole.text = model.userRole
        holder.userImage.setImageResource(model.userImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}