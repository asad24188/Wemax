package com.wemax.mtech.Adapter.home.serviceDetails

//import com.iamageo.library.AnotherReadMore
//import com.borjabravo.readmoretextview.ReadMoreTextView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.wemax.mtech.Model.serviceDetailsModel.ServicesReviewsModel
import com.wemax.mtech.R
import com.willy.ratingbar.ScaleRatingBar


class ServiceReviewsAdapter(val context: Context, val list: ArrayList<ServicesReviewsModel>) :
    RecyclerView.Adapter<ServiceReviewsAdapter.myViewHolder>() {


    class myViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val userName = itemview.findViewById<TextView>(R.id.userName)
        val userImage = itemview.findViewById<ImageView>(R.id.userProfileImage)
        val commentDateAndTime =
            itemview.findViewById<TextView>(R.id.commentDateAndTime)

        val commentText = itemview.findViewById<ReadMoreTextView>(R.id.commentText)

        //        val commentText = itemview.findViewById<TextView>(R.id.commentText)
        val ratingStarValue = itemview.findViewById<TextView>(R.id.reviewValue)
        val ratBar = itemview.findViewById<ScaleRatingBar>(R.id.ratingBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
//        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_service_review, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val model: ServicesReviewsModel = list.get(position)

        holder.userName.text = model.userName
        holder.commentDateAndTime.text = model.commentDateAndTime
//        holder.commentText.text = model.commentText
        holder.ratingStarValue.text = model.ratingStarValue
//        holder.userImage.setImageResource(R.drawable.user_image)
        holder.userImage.setImageResource(model.userImage)

        holder.commentText.text = model.commentText + " "

//        ref link: https://github.com/williamyyu/SimpleRatingBar
//        https://jitpack.io/p/ome450901/simpleratingbar
//        https://stackoverflow.com/questions/35378098/how-can-i-set-the-color-of-android-rating-bars-stroke-not-the-color-of-the-st
//        holder.ratBar.setRating(3.67f);
        holder.ratBar.setRating(model.ratingStarValue.toFloat());

/*


        val anotherReadMore: AnotherReadMore = AnotherReadMore.Builder(context)
            .textLength(90, AnotherReadMore.TYPE_LINE)
            .moreLabel(context.getString(R.string.read_more))
            .lessLabel(context.getString(R.string.read_less))
            .build()


        anotherReadMore.addReadMoreTo(
            holder.commentText, context.getString(R.string.review_text) + " "
        )

*/

    }

    override fun getItemCount(): Int {
        return list.size
    }


}