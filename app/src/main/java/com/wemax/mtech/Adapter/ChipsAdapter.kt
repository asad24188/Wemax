package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.R

class ChipsAdapter(val context: Context, val list: ArrayList<ChipsTagsModel>) :
    RecyclerView.Adapter<ChipsAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val interest = itemView.findViewById<TextView>(R.id.tv_interest)
        val parent = itemView.findViewById<RelativeLayout>(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chips_interest, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.interest.text = model.tag

        holder.parent.setOnClickListener {


            if (holder.interest.currentTextColor == context.resources.getColor(R.color.see_all_text)){
                holder.interest.setTextColor(ContextCompat.getColor(context, R.color.blue));
            } else {
                holder.interest.setTextColor(ContextCompat.getColor(context, R.color.see_all_text));
            }

            val drawableAConstantState =
                ContextCompat.getDrawable(context, R.drawable.interest_chips_bg)?.constantState
            holder.interest.setBackgroundResource(
                if (holder.interest.background?.constantState == drawableAConstantState) {
                    R.drawable.selected_interest_chips_bg


                } else {
                    R.drawable.interest_chips_bg
                }

            )

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}