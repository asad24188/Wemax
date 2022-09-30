package com.wemax.mtech.Adapter.notification

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Activity.ReminderDetailsActivity
import com.wemax.mtech.Model.notification.ModelNotification
import com.wemax.mtech.R

class NotificationAdapter(val context: Context, val notification: List<ModelNotification>) :
    RecyclerView.Adapter<NotificationAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*        var image=itemView.findViewById<ImageView>(R.id.image)
                var message= itemView.findViewById<TextView>(R.id.message)
                var title= itemView.findViewById<TextView>(R.id.title)
                var time= itemView.findViewById<TextView>(R.id.time)*/

        var image = itemView.findViewById<ImageView>(R.id.userImage)
        var accept_layout = itemView.findViewById<RelativeLayout>(R.id.Accept_layout)
        var message = itemView.findViewById<TextView>(R.id.tv_requiestby)
        var title = itemView.findViewById<TextView>(R.id.tv_descrip)
        var time = itemView.findViewById<TextView>(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_friend_requist, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val obj = notification.get(position)
        holder.image.setImageResource(obj.Image)
        /* holder.time.text = obj.Title
         holder.message.text = obj.Desc
         holder.time.text = obj.UploadOn*/
        holder.message.text = obj.Title
        holder.title.text = obj.Desc
        holder.time.text = obj.UploadOn
        holder.accept_layout.setOnClickListener {
            context.startActivity(Intent(context, ReminderDetailsActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return notification.size
    }
}