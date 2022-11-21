package com.wemax.mtech.Adapter.groups

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.activity.groups.GroupPostsActivity
import com.wemax.mtech.Model.groups.GroupsModelInFragment
import com.wemax.mtech.R

class GroupsAdapterInFragment(val context: Context, val list: ArrayList<GroupsModelInFragment>) :
    RecyclerView.Adapter<GroupsAdapterInFragment.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val group_dp_profileImage = itemView.findViewById<ImageView>(R.id.groupImage)
        val groupName = itemView.findViewById<TextView>(R.id.groupName)
        val totalGroupMembers = itemView.findViewById<TextView>(R.id.totalGroupMembers)
        val totalPostsADay = itemView.findViewById<TextView>(R.id.totalPostsAday)
        val groupPrivacyStatus = itemView.findViewById<TextView>(R.id.groupPrivacyStatus)
        val parentItemDetail = itemView.findViewById<CardView>(R.id.parentItemDetail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_group_in_group_fragment, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.group_dp_profileImage.setImageResource(model.groupProfileImage)
        holder.groupName.text = model.groupName
        holder.totalGroupMembers.text = model.totalGroupMembers + " Members"
        holder.totalPostsADay.text = model.totalGroupPostsADay + " Post a day"
        holder.groupPrivacyStatus.text = model.groupPrivacyStatus + " Group"
        holder.parentItemDetail.setOnClickListener{
            context.startActivity(Intent(context, GroupPostsActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}