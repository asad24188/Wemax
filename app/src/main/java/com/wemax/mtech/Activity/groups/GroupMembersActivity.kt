package com.wemax.mtech.Activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.FriendsActivity
import com.wemax.mtech.Adapter.groups.GroupMembersAdapter
import com.wemax.mtech.Model.groups.GroupMembersModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityGroupMembersBinding
import com.wemax.mtech.databinding.ActivityGroupPostsBinding

class GroupMembersActivity : AppCompatActivity() {
    lateinit var binding: ActivityGroupMembersBinding
    val contextActivity = this@GroupMembersActivity
    lateinit var utils: Utilities

    var listGroupMembers: ArrayList<GroupMembersModel> = arrayListOf()
    lateinit var adapter: GroupMembersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupMembersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {
        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.floatbtnLayout.setOnClickListener {
            startActivity(Intent(contextActivity, FriendsActivity::class.java)) }


        binding.groupMemebersRecycler.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.groupMemebersRecycler.adapter = GroupMembersAdapter(contextActivity, listGroupMembers)

    }

    private fun initRecyclerView() {
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.user1,
                resources.getString(R.string.cordelia_john),
                "Admin"
            )
        )
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.user3,
                resources.getString(R.string.cordelia_john),
                "User"
            )
        )
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.user2,
                resources.getString(R.string.cordelia_john),
                ""
            )
        )
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.user1,
                resources.getString(R.string.cordelia_john),
                ""
            )
        )
    }

}