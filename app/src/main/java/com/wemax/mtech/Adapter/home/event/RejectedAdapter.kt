package com.wemax.mtech.Adapter.home.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Model.AcceptedModel
import com.wemax.mtech.R

class RejectedAdapter(val context: Context, val list: ArrayList<AcceptedModel>) :
    RecyclerView.Adapter<RejectedAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event_member, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userName.text = model.userName
        holder.userImage.setImageResource(model.userImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}