package com.wemax.mtech.Adapter.home.event

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.activity.home.event.EventMembersActivity
import com.wemax.mtech.Model.home.event.UsersGoingModelClass
import com.wemax.mtech.R

class UsersGoingAdapter(val context: Context, val list: ArrayList<UsersGoingModelClass>) :
    RecyclerView.Adapter<UsersGoingAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImage)
        val parent = itemView.findViewById<LinearLayout>(R.id.parent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_users_going, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
//        holder.userImage.setImageResource(R.drawable.user1)
        holder.userImage.setImageResource(model.userImage)
        holder.parent.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    EventMembersActivity::class.java
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
