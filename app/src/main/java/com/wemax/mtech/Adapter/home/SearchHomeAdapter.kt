package com.wemax.mtech.Adapter.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.home.SearchModel
import com.wemax.mtech.R
import com.wemax.mtech.ServiceDetialsActivity

class SearchHomeAdapter(val context: Context, val booking: List<SearchModel>) :
    RecyclerView.Adapter<SearchHomeAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        var profile_image = itemView.findViewById<ShapeableImageView>(R.id.image)
        var tickImage = itemView.findViewById<ImageView>(R.id.iconImage)
        var searcheTitle = itemView.findViewById<TextView>(R.id.searcheTitle)
        var parentSearch = itemView.findViewById<RelativeLayout>(R.id.parentSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_search_layout, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val obj = booking.get(position)
        holder.tickImage.setImageResource(obj.searhedItemImageTick)
        holder.searcheTitle.text = obj.searchItemText
        holder.parentSearch.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return booking.size
    }
}