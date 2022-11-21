package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.notification.DataModelUpcomingsAppoints
import com.wemax.mtech.R

class AdapterUpcomingsApoints(val context : Context, val list: ArrayList<DataModelUpcomingsAppoints>) :
    RecyclerView.Adapter<AdapterUpcomingsApoints.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.image)
        val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        val dateText: TextView = itemView.findViewById(R.id.dateText)
        val statTime: TextView = itemView.findViewById(R.id.statTime)
        val endTime: TextView = itemView.findViewById(R.id.endTime)
        var parentProductDetail = itemView.findViewById<CardView>(R.id.parentProductDetail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_upcoming_appoints, parent, false)
        return AdapterUpcomingsApoints.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: DataModelUpcomingsAppoints = list.get(position)
        holder.image.setImageResource(model.image)
        holder.postTitle.text = model.tittle
        holder.dateText.text = model.date
        holder.statTime.text = model.starttime
        holder.endTime.text = model.endtime

        holder.parentProductDetail.setOnClickListener {
//            context.startActivity(Intent(context, MyAppointmentsDetailsActivity::class.java))

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}