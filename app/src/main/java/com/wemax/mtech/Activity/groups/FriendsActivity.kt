package com.wemax.mtech.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.FriendsAdapter
import com.wemax.mtech.Adapter.groups.GroupMembersAdapter
import com.wemax.mtech.Model.groups.GroupMembersModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityFriendsBinding

class FriendsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFriendsBinding
    val contextActivity = this@FriendsActivity
    var listGroupMembers: ArrayList<GroupMembersModel> = arrayListOf()
    lateinit var adapter: FriendsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        onClicks()

    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.friendsRecycler.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.friendsRecycler.adapter = FriendsAdapter(contextActivity, listGroupMembers)
    }
    private fun initRecyclerView() {
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john),
                "Send Request",
            )
        )
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive),
                "Requested"
            )
        )
        listGroupMembers.add(
            GroupMembersModel(
                "",
                R.drawable.ic_user_img1,
                resources.getString(R.string.william),
                "Send Request"
            )
        )

    }
}