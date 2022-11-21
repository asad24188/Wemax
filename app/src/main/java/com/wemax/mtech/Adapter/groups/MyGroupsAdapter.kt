package com.wemax.mtech.Adapter.groups

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.activity.groups.GroupPostsActivity
import com.wemax.mtech.Model.groups.MyGroupsModel
import com.wemax.mtech.R

class MyGroupsAdapter(val context: Context, val list: ArrayList<MyGroupsModel>) :
    RecyclerView.Adapter<MyGroupsAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val group_dp_profileImage = itemView.findViewById<ShapeableImageView>(R.id.groupImage)
        val groupName = itemView.findViewById<TextView>(R.id.groupName)
        val totalGroupMembers = itemView.findViewById<TextView>(R.id.totalGroupMembers)
        val totalPostsADay = itemView.findViewById<TextView>(R.id.totalPostsAday)
        val parentLayout = itemView.findViewById<LinearLayout>(R.id.parentLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_my_groups, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.group_dp_profileImage.setImageResource(model.groupProfileImage)
        holder.groupName.text = model.groupName
        holder.totalGroupMembers.text = model.totalGroupMembers + " Members"
        holder.totalPostsADay.text = model.totalGroupPostsADay + " Post a day"
        holder.parentLayout.setOnClickListener {
            context.startActivity(Intent(context, GroupPostsActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}