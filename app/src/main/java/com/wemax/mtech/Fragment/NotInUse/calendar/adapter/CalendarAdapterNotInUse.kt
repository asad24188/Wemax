package com.wemax.mtech.Fragment.calendar.adapter

import android.graphics.Color
import android.view.ViewGroup
import android.view.LayoutInflater
import com.wemax.mtech.R
import androidx.annotation.RequiresApi
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Fragment.NotInUse.calendar.CalendarUtils
import com.wemax.mtech.Fragment.NotInUse.calendar.CalendarViewHolder
import java.time.LocalDate
import java.util.ArrayList

class CalendarAdapterNotInUse(
    private val days: ArrayList<LocalDate>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams
        if (days.size > 15) //month view
            layoutParams.height = (parent.height * 0.166666666).toInt() else  // week view
            layoutParams.height = parent.height
        return CalendarViewHolder(view, onItemListener, days)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = days[position]
        holder.dayOfMonth.text = date.dayOfMonth.toString()
        if (date == CalendarUtils.selectedDate) {
            holder.parentView.setBackgroundColor(Color.LTGRAY)
/*            holder.parentView.setBackgroundColor(R.color.blue)
            holder.dayOfMonth.setTextColor(Color.WHITE)*/
        }
        if (date.month == CalendarUtils.selectedDate.month) {
//            holder.dayOfMonth.setTextColor(Color.BLACK)
            holder.dayOfMonth.setTextColor(Color.BLACK)
        } else {
/*            holder.dayOfMonth.setTextColor(
                Color.LTGRAY
            ) */
            holder.dayOfMonth.setTextColor(
                Color.WHITE
            )
        }

    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate?)
    }
}