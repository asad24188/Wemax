package com.wemax.mtech.Adapter.home.serviceDetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Activity.OTPVerifyApointmentActivity
import com.wemax.mtech.Model.serviceDetailsModel.ServicesMoreModel
import com.wemax.mtech.R

class PickAServiceAdapter(
    val context: Context,
    val list: ArrayList<ServicesMoreModel>,
    val clicklistner: onItemClickListner_interfaceName,
    val layoutInflater: LayoutInflater
) :
    RecyclerView.Adapter<PickAServiceAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val seriveImage: ShapeableImageView =
            itemView.findViewById<ShapeableImageView>(R.id.serviceImage)
        val serviceName: TextView = itemView.findViewById<TextView>(R.id.serviceName)
        val serviceTime: TextView = itemView.findViewById<TextView>(R.id.serviceTime)
        val serviceCost: TextView = itemView.findViewById<TextView>(R.id.serviceCost)
        val parentLayout: RelativeLayout = itemView.findViewById<RelativeLayout>(R.id.parentt)
        val priceDetailsLayout: LinearLayout =
            itemView.findViewById<LinearLayout>(R.id.priceDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val View_layoutInflator: View =
            LayoutInflater.from(context).inflate(R.layout.item_service_more, parent, false)
        return myViewHolder(View_layoutInflator)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: ServicesMoreModel = list.get(position)
        holder.serviceName.text = model.serviceName
        holder.serviceTime.text = model.serviceTime
        holder.serviceCost.text = model.serviceCost
//        holder.seriveImage.setImageResource(R.drawable.hair_style_image)
        holder.seriveImage.setImageResource(model.serviceImage)

        holder.parentLayout.setOnClickListener {
            getDataDialog()
        }
        /*holder.seriveImage.setOnClickListener {
            context.startActivity(Intent(context, PhotoGalleryActivity::class.java))
        }
        holder.priceDetailsLayout.setOnClickListener {
            context.startActivity(Intent(context, SelectDateAndTimeActivity::class.java))
        }
*/

        clicklistner.onItemClickmyMethod_amd(list)
//        clicklistner.onItemClickmyMethod_amd2(list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface onItemClickListner_interfaceName {
        fun onItemClickmyMethod_amd(listt: List<ServicesMoreModel>)

        fun onItemClickmyMethod_amd2(listt: List<ServicesMoreModel>)
    }


    private fun getDataDialog() {

        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_confirmation, null)
        val text = view.findViewById<TextView>(R.id.serviceDetials)
        val btnAccept = view.findViewById<CardView>(R.id.accept)

        btnAccept.setOnClickListener { builder.dismiss()
        context.startActivity(Intent(context, OTPVerifyApointmentActivity::class.java))}

        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}