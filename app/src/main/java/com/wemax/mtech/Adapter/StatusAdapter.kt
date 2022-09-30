package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.AddressDataModel
import com.wemax.mtech.Model.StatusModel
import com.wemax.mtech.R

class StatusAdapter(
    val context: Context,
    val listModel: ArrayList<StatusModel>
) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val address: TextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_status, parent, false)
        return StatusAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: StatusModel = listModel.get(position)

        holder.address.text = model.Status
    }

    override fun getItemCount(): Int {
        return listModel.size
    }
}