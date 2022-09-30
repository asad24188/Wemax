package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.DataModelSharedWith
import com.wemax.mtech.R

class AdapterSharedWith(val context: Context, val list: ArrayList<DataModelSharedWith>) :
    RecyclerView.Adapter<AdapterSharedWith.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.userImage)
        val name: TextView = itemView.findViewById(R.id.tv_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reminder_detail, parent, false)
        return AdapterSharedWith.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: DataModelSharedWith = list.get(position)
        holder.image.setImageResource(model.image)
        holder.name.text = model.name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}