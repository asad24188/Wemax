package com.wemax.mtech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.R

class InviteFriendsAdapter(val context: Context, val list: ArrayList<NewReminderModel>) :
    RecyclerView.Adapter<InviteFriendsAdapter.myViewHolder>() {

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImage)
        val imageSentTick = itemView.findViewById<ImageView>(R.id.ivcheck)
        val sendInvitaionButton = itemView.findViewById<TextView>(R.id.userRole)
        val parent = itemView.findViewById<LinearLayout>(R.id.parent)
        val sendinvitaionText = itemView.findViewById<TextView>(R.id.userRole)
        var statusInvitaionSent: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sharedwith_friend, parent, false)
        return InviteFriendsAdapter.myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userName.text = model.userName
        holder.userImage.setImageResource(model.userImage)
        holder.statusInvitaionSent == model.status
        holder.sendinvitaionText.text = "Send Invite"
        holder.parent.setOnClickListener {
            if (holder.statusInvitaionSent.equals(false)){
                holder.statusInvitaionSent = true
                holder.sendInvitaionButton.visibility = View.GONE
                holder.imageSentTick.visibility = View.VISIBLE
            } else {
                holder.statusInvitaionSent = false
                holder.sendInvitaionButton.visibility = View.VISIBLE
                holder.imageSentTick.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}