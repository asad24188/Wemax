package com.wemax.mtech.Adapter.home.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.home.event.BringingModelClass
import com.wemax.mtech.Model.home.event.LabelsModelClass
import com.wemax.mtech.R
import java.util.ArrayList

class LabelAdapter(
    val context: Context,
    val list: ArrayList<LabelsModelClass>
) : RecyclerView.Adapter<LabelAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.labelName)
        val quantity = itemView.findViewById<TextView>(R.id.quantity)
        val pickedBy = itemView.findViewById<TextView>(R.id.pickedBy)
        val btnClose = itemView.findViewById<RelativeLayout>(R.id.btnClose)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_custom_label, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: LabelsModelClass = list.get(position)
        holder.itemName.text = model.Item
        holder.quantity.text = model.Quty
        holder.pickedBy.text = model.Name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}