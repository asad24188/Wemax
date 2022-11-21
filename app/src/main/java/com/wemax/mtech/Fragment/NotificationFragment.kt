package com.wemax.mtech.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.activity.notification.ProfileActivity
import com.wemax.mtech.Adapter.notification.NotificationAdapter
import com.wemax.mtech.Model.notification.ModelNotification
import com.wemax.mtech.R
import com.wemax.mtech.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    lateinit var newArrayList:ArrayList<ModelNotification>
    private var _binding:FragmentNotificationBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.icprofile.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }


        binding.recyclerView.layoutManager= LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        newArrayList= arrayListOf<ModelNotification>()
        getUserNotifications()
        binding.recyclerView.adapter= NotificationAdapter(requireContext(),newArrayList)


        return view
    }

    private fun getUserNotifications() {
        newArrayList= arrayListOf()
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
        newArrayList.add(ModelNotification(R.drawable.ic_user_img3,resources.getString(R.string.olive),resources.getString(R.string.send_you_a_friend_request),resources.getString(R.string._25_july_2022)))
      }
}