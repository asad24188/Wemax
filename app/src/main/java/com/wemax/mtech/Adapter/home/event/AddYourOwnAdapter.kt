package com.wemax.mtech.Adapter.home.event

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.activity.home.event.AddYourOwnActivity2
import com.wemax.mtech.Model.home.event.AddYourOwnModel
import com.wemax.mtech.R
import java.util.ArrayList

class AddYourOwnAdapter(
    val context: Context,
    val list: ArrayList<AddYourOwnModel>
) : RecyclerView.Adapter<AddYourOwnAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.itemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_add_your_own, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: AddYourOwnModel = list.get(position)
        holder.itemName.text = model.Label
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,AddYourOwnActivity2::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}