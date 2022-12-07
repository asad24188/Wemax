package com.wemax.mtech.Adapter.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.homeApi.*
import com.wemax.mtech.R
import com.wemax.mtech.ServiceDetialsActivity
import com.wemax.mtech.utils.Constants

class HomeFragmentServicesAdapter(val context: Context,
                                  val action: String,
                                  val recommended_businesses: ArrayList<RecommendedBusinesse>,
                                  val hot_events: ArrayList<HotEvent>,
                                  val activities: ArrayList<Activity>,
                                  val nearby_providers: ArrayList<NearbyProvider>,
                                  val events_next_to_me: ArrayList<EventsNextToMe>,
                                  val cleaning_service_around_me: ArrayList<CleaningServiceAroundMe>,
                                  val people_also_viewed: ArrayList<PeopleAlsoViewed>,
                                  val nail_salons_around_me: ArrayList<NailSalonsAroundMe>,
)
    : RecyclerView.Adapter<HomeFragmentServicesAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var serviceImage = itemView.findViewById<ImageView>(R.id.serviceImage)
        var serviceTitle = itemView.findViewById<TextView>(R.id.serviceTitle)
        var serviceRating = itemView.findViewById<TextView>(R.id.serviceRating)
        var serviceDistance = itemView.findViewById<TextView>(R.id.serviceDistance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_home_fragment_services, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        when (action){
            Constants.RECOMMENDED_BUSINESS -> {

                val obj = recommended_businesses.get(position)
                Glide.with(context).load(obj.profile_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
                holder.serviceRating.text = obj.average_rating

            }
            Constants.HOT_EVENT -> {

                val obj = hot_events.get(position)
                Glide.with(context).load(obj.event_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.event_name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
                holder.serviceRating.text = "5.0"
            }

            Constants.ACTIVITIES -> {

                val obj = activities.get(position)
                Glide.with(context).load(obj.activity_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.activity_name
                holder.serviceDistance.visibility = View.GONE

            }
            Constants.NEARBY_PROVIDERS -> {

                val obj = nearby_providers.get(position)
                Glide.with(context).load(obj.profile_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
            }
            Constants.EVENT_NEXT_TO_ME -> {

                val obj = events_next_to_me.get(position)
                Glide.with(context).load(obj.event_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.event_name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
            }
            Constants.CLEANING_SERVICE -> {

                val obj = cleaning_service_around_me.get(position)
                Glide.with(context).load(obj.subcategory_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.subcategory_name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
            }
            Constants.ALSO_VIEWED -> {

                val obj = people_also_viewed.get(position)
                Glide.with(context).load(obj.profile_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
            }
            Constants.NAIL_SALON_AROUND -> {

                val obj = nail_salons_around_me.get(position)
                Glide.with(context).load(obj.subcategory_image).into(holder.serviceImage)
                holder.serviceTitle.text = obj.subcategory_name
                holder.serviceDistance.text = obj.distance.toString()+" KM"
            }
        }

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, ServiceDetialsActivity::class.java))

        }
    }

    override fun getItemCount(): Int {

        when (action){

            Constants.RECOMMENDED_BUSINESS -> { return recommended_businesses.size }

            Constants.HOT_EVENT -> { return hot_events.size }

            Constants.ACTIVITIES -> { return activities.size }

            Constants.NEARBY_PROVIDERS -> { return nearby_providers.size }

            Constants.EVENT_NEXT_TO_ME -> { return events_next_to_me.size }

            Constants.CLEANING_SERVICE -> { return cleaning_service_around_me.size }

            Constants.ALSO_VIEWED -> { return recommended_businesses.size }

            Constants.NAIL_SALON_AROUND -> { return nail_salons_around_me.size }
        }

        return 0
    }
}