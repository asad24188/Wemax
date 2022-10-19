package com.wemax.mtech.Adapter.groups

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.Activity.groups.GroupPostsActivity
import com.wemax.mtech.Model.groups.MyGroupsModel
import com.wemax.mtech.Model.groups.SelectFriendsModel
import com.wemax.mtech.R

class SelectFriendsAdapter(val context: Context, val list: ArrayList<SelectFriendsModel>, val layoutInflater: LayoutInflater


) :
    RecyclerView.Adapter<SelectFriendsAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImageInComment)
        val onlineImage = itemView.findViewById<ImageView>(R.id.onlineImage)
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val onlineStatus = itemView.findViewById<TextView>(R.id.offlineStatus)
        var parentLayout = itemView.findViewById<LinearLayout>(R.id.parentLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_select_friend, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.userImage.setImageResource(model.userImage)
        holder.userName.text = model.userName
        if (model.userOnlineStatus.equals(true)) {
            holder.onlineStatus.text = "online"
            holder.onlineImage.setImageResource(R.drawable.online_image)
        } else {
            holder.onlineStatus.text = "offline"
            holder.onlineImage.setImageResource(R.drawable.offline_image)
        }
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
        val view = layoutInflater.inflate(R.layout.popup_how_long, null)
        val btnAccept = view.findViewById<TextView>(R.id.ok)

        btnAccept.setOnClickListener {
            builder.dismiss()
        }


        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}