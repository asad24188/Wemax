package com.wemax.mtech.Adapter.home.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.home.event.BringingModelClass
import com.wemax.mtech.R
import java.util.ArrayList

class LabelsBringingListAdapter(
    val context: Context,
    val list: ArrayList<BringingModelClass>
) : RecyclerView.Adapter<LabelsBringingListAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelName = itemView.findViewById<TextView>(R.id.labelName)
        val cancelImage = itemView.findViewById<ImageView>(R.id.iconImageClose)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.labels_bringing_ist_list, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: BringingModelClass = list.get(position)
        holder.labelName.text = model.labelName
        holder.cancelImage.setImageResource(model.imageAction)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}