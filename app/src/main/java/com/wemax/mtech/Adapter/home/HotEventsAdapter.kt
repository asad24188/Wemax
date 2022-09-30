package com.wemax.mtech.Adapter.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Activity.home.event.EventDetailsActivity
import com.wemax.mtech.Model.home.HotEventsPostModel
import com.wemax.mtech.R

class HotEventsAdapter(val context: Context, val booking: List<HotEventsPostModel>) :
    RecyclerView.Adapter<HotEventsAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        var profile_image = itemView.findViewById<ShapeableImageView>(R.id.image)
        var profile_image = itemView.findViewById<ImageView>(R.id.image)
        var providerName = itemView.findViewById<TextView>(R.id.postTitle)
        var providerRating = itemView.findViewById<TextView>(R.id.rating)
        var postDistance = itemView.findViewById<TextView>(R.id.postDistance)
        var parentProductDetail = itemView.findViewById<CardView>(R.id.parentProductDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_layout_hot_events, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val obj = booking.get(position)
        holder.profile_image.setImageResource(obj.postImage)
        holder.providerName.text = obj.postTitle
        holder.providerRating.text = obj.postRating
        holder.postDistance.text = obj.postDistanceTime
        holder.parentProductDetail.setOnClickListener {
            context.startActivity(Intent(context, EventDetailsActivity::class.java))

        }
    }

    override fun getItemCount(): Int {
        return booking.size
    }
}