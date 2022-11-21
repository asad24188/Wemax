package com.wemax.mtech.Adapter.home.serviceDetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.activity.home.serviceDetails.PhotoGalleryActivity
import com.wemax.mtech.activity.home.serviceDetails.SelectDateTimeActivity
import com.wemax.mtech.Model.serviceDetailsModel.ServicesMoreModel
import com.wemax.mtech.R

class ServicesMoreAdapter(val context: Context, val list: ArrayList<ServicesMoreModel>) :
    RecyclerView.Adapter<ServicesMoreAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val seriveImage: ShapeableImageView =
            itemView.findViewById<ShapeableImageView>(R.id.serviceImage)
        val serviceName: TextView = itemView.findViewById<TextView>(R.id.serviceName)
        val serviceTime: TextView = itemView.findViewById<TextView>(R.id.serviceTime)
        val serviceCost: TextView = itemView.findViewById<TextView>(R.id.serviceCost)
        val priceDetailsLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.priceDetails)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_service_more, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: ServicesMoreModel = list.get(position)
        holder.serviceName.text = model.serviceName
        holder.serviceTime.text = model.serviceTime
        holder.serviceCost.text = model.serviceCost
//        holder.seriveImage.setImageResource(R.drawable.hair_style_image)
        holder.seriveImage.setImageResource(model.serviceImage)
        holder.seriveImage.setOnClickListener {
            context.startActivity(Intent(context, PhotoGalleryActivity::class.java))
        }
        holder.priceDetailsLayout.setOnClickListener {
            context.startActivity(Intent(context, SelectDateTimeActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}