package com.wemax.mtech.Adapter.home.serviceDetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.activity.home.serviceDetails.PickaServiceActivity
import com.wemax.mtech.Model.calendarReminder.CalendarReminderModel
import com.wemax.mtech.R

class SelectDateAndTimeAdapter(val context: Context, val list: ArrayList<CalendarReminderModel>) :
    RecyclerView.Adapter<SelectDateAndTimeAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reminderTime = itemView.findViewById<TextView>(R.id.timeTV)
        val eventName = itemView.findViewById<TextView>(R.id.eventName)
        val eventDate = itemView.findViewById<TextView>(R.id.eventDate)
        val parentLayout = itemView.findViewById<LinearLayout>(R.id.parentLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_hour_cell_yellow_bg, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.reminderTime.text = model.reminderTime
        holder.eventName.text = model.eventName
        holder.eventDate.text = model.eventDate
        holder.parentLayout.setOnClickListener {
            context.startActivity(Intent(context, PickaServiceActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}