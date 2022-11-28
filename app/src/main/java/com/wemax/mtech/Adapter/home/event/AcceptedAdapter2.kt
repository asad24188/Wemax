package com.wemax.mtech.Adapter.home.event

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Model.AcceptedModel
import com.wemax.mtech.R
import java.util.*
import kotlin.collections.ArrayList

class AcceptedAdapter2(val context: Context, val list: ArrayList<AcceptedModel>,
                       val layoutInflater: LayoutInflater
) :
    RecyclerView.Adapter<AcceptedAdapter2.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImage)
        var parentLayout = itemView.findViewById<LinearLayout>(R.id.parentlayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event_member, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userName.text = model.userName
        holder.userImage.setImageResource(model.userImage)

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
        val view = layoutInflater.inflate(R.layout.popup_successfully_sent, null)
        val cancelImageLayout = view.findViewById<LinearLayout>(R.id.cancelImageLayout)

        cancelImageLayout.setOnClickListener {
            builder.dismiss()
        }
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

}