package com.wemax.mtech.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.activity.home.PlaceDetailsActivity
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.R

class AdapterRecomendedFor(val context: Context, val list: ArrayList<PostModel>) :
    RecyclerView.Adapter<AdapterRecomendedFor.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image = itemView.findViewById<ImageView>(R.id.image)
        var name = itemView.findViewById<TextView>(R.id.postTitle)
        var rating = itemView.findViewById<TextView>(R.id.rating)
        var parentProductDetail = itemView.findViewById<CardView>(R.id.parentProductDetail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_home_fragment_services, parent, false)
        return AdapterRecomendedFor.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: PostModel = list.get(position)
        holder.image.setImageResource(model.postImage)
        holder.name.text = model.postTitle
        holder.rating.text = model.postRating
        holder.parentProductDetail.setOnClickListener {
//            context.startActivity(Intent(context, PlaceDetailsActivity::class.java))
            context.startActivity(Intent(context, PlaceDetailsActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}