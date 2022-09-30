package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.AddressDataModel
import com.wemax.mtech.R

class AddressAdapter(
    val context: Context,
    val listModel: ArrayList<AddressDataModel>
) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val address: TextView = itemView.findViewById(R.id.tv_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_address, parent, false)
        return AddressAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: AddressDataModel = listModel.get(position)

        holder.address.text = model.address
    }

    override fun getItemCount(): Int {
        return listModel.size
    }
}