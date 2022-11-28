package com.wemax.mtech.Adapter

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Model.notification.DataModelUpcomingsAppoints
import com.wemax.mtech.R
import com.wemax.mtech.ServiceDetialsActivity
import java.util.*
import kotlin.collections.ArrayList

class AdapterUpcomingsApoints(
    val context: Context,
    val list: ArrayList<DataModelUpcomingsAppoints>,
    val layoutInflater: LayoutInflater

) :
    RecyclerView.Adapter<AdapterUpcomingsApoints.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.image)
        val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        val dateText: TextView = itemView.findViewById(R.id.dateText)
        val statTime: TextView = itemView.findViewById(R.id.statTime)
        val endTime: TextView = itemView.findViewById(R.id.endTime)
        var parentLayout = itemView.findViewById<LinearLayout>(R.id.parentLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_upcoming_appoints, parent, false)
        return AdapterUpcomingsApoints.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: DataModelUpcomingsAppoints = list.get(position)
        holder.image.setImageResource(model.image)
        holder.postTitle.text = model.tittle
        holder.dateText.text = model.date
        holder.statTime.text = model.starttime
        holder.endTime.text = model.endtime

        holder.parentLayout.setOnClickListener {
            getDataDialog()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getDataDialog() {

        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_coustom_date, null)
        val btnAccept = view.findViewById<CardView>(R.id.btnsave)

        btnAccept.setOnClickListener {
            builder.dismiss()
        }
        val edttxt_date=view.findViewById<TextView>(R.id.layout1)    //edit text declaration
        val edttxt_date2=view.findViewById<TextView>(R.id.layout2)    //edit text declaration
        val calendar= Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        edttxt_date.setOnClickListener {
            val datePickerDialog= DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datepicker, i, i2, i3 ->
                edttxt_date.setText(i3.toString()+"/ "+(i2+1).toString()+"/ "+i.toString())
            },year, month,day)
            datePickerDialog.show()
        }
        edttxt_date2.setOnClickListener {
            val datePickerDialog= DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datepicker, i, i2, i3 ->
                edttxt_date2.setText(i3.toString()+"/ "+(i2+1).toString()+"/ "+i.toString())
            },year, month,day)
            datePickerDialog.show()
        }
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}