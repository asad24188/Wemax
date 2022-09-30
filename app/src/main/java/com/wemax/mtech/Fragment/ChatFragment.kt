package com.wemax.mtech.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Activity.chat.StatusActivity
import com.wemax.mtech.Activity.groups.CreateGroupActivity
import com.wemax.mtech.Activity.groups.SelectFriendsActivity
import com.wemax.mtech.Adapter.ChatAdapterr
import com.wemax.mtech.Adapter.groups.PostCommentsAdapter
import com.wemax.mtech.Model.groups.PostCommentsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.FragmentChatBinding
import com.wemax.mtech.databinding.FragmentNotificationBinding

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    val contextActivity = this@ChatFragment

    lateinit var adapterComments: ChatAdapterr
    var listComments: ArrayList<PostCommentsModel> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root



        onClick()
        return view
    }

    private fun onClick() {

        binding.floatbtnLayout.setOnClickListener {
            if (binding.fabOptions.visibility==View.GONE){
                binding.fabOptions.visibility=View.VISIBLE
            }else{
                binding.fabOptions.visibility=View.GONE
            }
        }
        binding.statust.setOnClickListener {
            startActivity(Intent(context, StatusActivity::class.java))
        }
        binding.fabOptions.setOnClickListener {
            startActivity(Intent(context, SelectFriendsActivity::class.java))
        }
//        binding.backLayout.setOnClickListener {
//            if (binding.fabOptions.visibility==View.GONE){
//                binding.fabOptions.visibility=View.VISIBLE
//                binding.backLayout.visibility=View.VISIBLE
//            }else{
//                binding.fabOptions.visibility=View.GONE
//                binding.backLayout.visibility=View.GONE
//            }
//        }
        initRecyclerView()
        binding.recyclerInbox.layoutManager = LinearLayoutManager(requireContext())
        adapterComments = ChatAdapterr(requireContext(), listComments)
        binding.recyclerInbox.adapter = adapterComments
    }

    private fun initRecyclerView() {
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.ic_user_img2,
                "I just invited you and Jacob to dinner on Friday.You better be there",
                "5 MINS AGO",
                )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Are you ready for bike riding Wednesday, \n" +
                        "can't wait!",
                "YESTERDAY",
            )
        )
        listComments.add(
            PostCommentsModel(
                "",
                "",
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                "Miss you, let's do something, I saw you're free \n" +
                        "this Saturday",
                "2 DAYS AGO",
            )
        )
    }
}