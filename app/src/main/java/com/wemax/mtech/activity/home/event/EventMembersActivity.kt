package com.wemax.mtech.activity.home.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.home.event.AcceptedAdapter
import com.wemax.mtech.Adapter.home.event.RejectedAdapter
import com.wemax.mtech.Model.AcceptedModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityEventMembersBinding

class EventMembersActivity : AppCompatActivity() {
    lateinit var binding: ActivityEventMembersBinding
    val contextActivity = this@EventMembersActivity

    var listGroupMembers: ArrayList<AcceptedModel> = arrayListOf()
    lateinit var adapter: AcceptedAdapter
    lateinit var adapterReject: RejectedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventMembersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        onClicks()

    }

    private fun onClicks() {

        binding.backpress.setOnClickListener { finish() }
        //Accepted_RecyclerView
        binding.recyclerAccepted.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerAccepted.adapter = AcceptedAdapter(contextActivity, listGroupMembers)
        //Rejected_RecyclerView
        binding.recyclerRejected.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerRejected.adapter = RejectedAdapter(contextActivity, listGroupMembers)
        //Respond_RecyclerView
        binding.recyclerRespond.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerRespond.adapter = RejectedAdapter(contextActivity, listGroupMembers)
    }
    private fun initRecyclerView() {
        listGroupMembers.add(
            AcceptedModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john)
            )
        )
        listGroupMembers.add(
            AcceptedModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive)
            )
        )
    }
}