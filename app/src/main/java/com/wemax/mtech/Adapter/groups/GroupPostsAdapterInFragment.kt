package com.wemax.mtech.Adapter.groups

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wemax.mtech.activity.groups.UserProfileActivity
import com.wemax.mtech.Model.groups.SinglePostModel
import com.wemax.mtech.R
import com.wemax.mtech.ServiceDetialsActivity

class GroupPostsAdapterInFragment(
    val context: Context,
    val list: ArrayList<SinglePostModel>
) :
    RecyclerView.Adapter<GroupPostsAdapterInFragment.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val group_dp_profileImage = itemView.findViewById<ShapeableImageView>(R.id.groupImage)
        val userImage = itemView.findViewById<ShapeableImageView>(R.id.userImage)
        val privacyStatusImage = itemView.findViewById<ImageView>(R.id.privacyStatusImage)
        val postImage = itemView.findViewById<ImageView>(R.id.postImage)
        val groupName = itemView.findViewById<TextView>(R.id.groupName)
        val userName = itemView.findViewById<TextView>(R.id.userName)
        val postTime = itemView.findViewById<TextView>(R.id.postTime)
        val postCaption = itemView.findViewById<TextView>(R.id.postCaption)
        val totalLikes = itemView.findViewById<TextView>(R.id.totalLikes)
        val totalComments = itemView.findViewById<TextView>(R.id.totalComments)
        val totalShares = itemView.findViewById<TextView>(R.id.totalShares)
        val bookNowBtn = itemView.findViewById<LinearLayout>(R.id.bookNowBtn)
        val postItemLayout = itemView.findViewById<RelativeLayout>(R.id.postItemLayout)
        val dottedOptionsMenu = itemView.findViewById<LinearLayout>(R.id.dottedOptionsMenu)
        val shareBtn = itemView.findViewById<LinearLayout>(R.id.sharesLayout)
        val likeBtn = itemView.findViewById<LinearLayout>(R.id.likessLayout)
        val commentBtn = itemView.findViewById<LinearLayout>(R.id.commentsLayout)
        val reportThisPostLayout = itemView.findViewById<CardView>(R.id.reportThisPostLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_post_group_fragment, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model = list.get(position)
        holder.group_dp_profileImage.setImageResource(model.groupImage)
        holder.userImage.setImageResource(model.userImage)
        holder.privacyStatusImage.setImageResource(model.postPrivacyStatus)
        holder.postImage.setImageResource(model.postImage)
        holder.groupName.text = model.groupName
        holder.userName.text = model.userName
        holder.postTime.text = model.postTime
        holder.postCaption.text = model.postCaption
        holder.totalLikes.text = model.postTotalLikes
        holder.totalComments.text = model.postTotalComments
        holder.totalShares.text = model.postTotalShares
/*
        holder.postImage.setOnClickListener {
            context.startActivity(Intent(context, PostDetailsActivity::class.java))
        }*/
        holder.bookNowBtn.setOnClickListener {
            context.startActivity(Intent(context, ServiceDetialsActivity::class.java))
        }

        holder.userImage.setOnClickListener {
            context.startActivity(Intent(context, UserProfileActivity::class.java))
        }
        holder.userName.setOnClickListener {
            context.startActivity(Intent(context, UserProfileActivity::class.java))
        }


        holder.dottedOptionsMenu.setOnClickListener {
            if (holder.reportThisPostLayout.visibility == View.GONE) {
                holder.reportThisPostLayout.visibility = View.VISIBLE
            } else {

                holder.reportThisPostLayout.visibility = View.GONE
            }

        }

        holder.reportThisPostLayout.setOnClickListener {

        }

        holder.shareBtn.setOnClickListener {

        }

        holder.likeBtn.setOnClickListener {

        }

        holder.commentBtn.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}