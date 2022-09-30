package com.wemax.mtech.Adapter.home.serviceDetails

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Activity.OTPVerifyApointmentActivity
import com.wemax.mtech.Activity.auth.TouchAppointmentActivity
import com.wemax.mtech.Activity.home.serviceDetails.PhotoGalleryActivity
import com.wemax.mtech.Activity.home.serviceDetails.SelectDateTimeActivity
import com.wemax.mtech.Model.home.serviceDetails.DateTimeModel
import com.wemax.mtech.Model.serviceDetailsModel.ServicesMoreModel
import com.wemax.mtech.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DateTimeAdapter(
    val context: Context,
    val list: ArrayList<DateTimeModel>, val layoutInflater: LayoutInflater
) :
    RecyclerView.Adapter<DateTimeAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val date: TextView = itemView.findViewById<TextView>(R.id.date)
        val time: TextView = itemView.findViewById<TextView>(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_appointments, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: DateTimeModel = list.get(position)
        holder.date.text = model.Date
        holder.time.text = model.Time
        holder.itemView.setOnClickListener {
            getDataDialog()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getDataDialog() {

        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_confirmation, null)
        val btnAccept = view.findViewById<CardView>(R.id.accept)
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        val btnNext = view.findViewById<CardView>(R.id.next)
        val btnSave = view.findViewById<CardView>(R.id.save)
        val txtSkip = view.findViewById<TextView>(R.id.tvSkip)
        val txtDate = view.findViewById<TextView>(R.id.tvDate)
        val txtTime = view.findViewById<TextView>(R.id.tvTime)
        val pickDate = view.findViewById<RelativeLayout>(R.id.pickDate)
        val pickTime = view.findViewById<RelativeLayout>(R.id.pickTime)
        val popup1 = view.findViewById<LinearLayout>(R.id.popup1)
        val popup2 = view.findViewById<LinearLayout>(R.id.popup2)
        val popup3 = view.findViewById<LinearLayout>(R.id.popup3)
        val etNote = view.findViewById<EditText>(R.id.etNote)
        btnAccept.setOnClickListener {
//            builder.dismiss()
            if (checkBox.isChecked) {
                popup1.visibility = View.GONE
                popup3.visibility = View.VISIBLE
            } else {
                popup1.visibility = View.GONE
                popup2.visibility = View.VISIBLE
            }
        }
        btnSave.setOnClickListener {
            popup3.visibility = View.GONE
            popup2.visibility = View.VISIBLE
        }
        btnNext.setOnClickListener {
            context.startActivity(
                Intent(context, TouchAppointmentActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        txtSkip.setOnClickListener {
            context.startActivity(
                Intent(context, TouchAppointmentActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
        pickDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    txtDate.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                }, year, month, day
            )
            dpd.show()
        }
        pickTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val hour = cal.get(Calendar.HOUR)
            val minute = cal.get(Calendar.MINUTE)

            TimePickerDialog(context,TimePickerDialog.OnTimeSetListener {
                    timePicker, p1, p2 ->
                txtTime.text = "$hour:$minute"
            } , hour, minute, true).show()
        }

        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}