package com.wemax.mtech.activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.activity.chat.ChatActivity
import com.wemax.mtech.Adapter.groups.GroupsAdapterInFragment
import com.wemax.mtech.Model.groups.GroupsModelInFragment
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityUserProfileBinding
import java.util.ArrayList

class UserProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserProfileBinding
    val contextActivity = this@UserProfileActivity
    lateinit var utils: Utilities


    lateinit var listGroups: ArrayList<GroupsModelInFragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
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
        groupsRecyclerView()
        binding.sendMessageLayout.setOnClickListener {
            startActivity(
                Intent(
                    contextActivity,
                    ChatActivity::class.java
                )
            )
        }
    }

    fun groupsRecyclerView() {
        binding.groupsRecyclerView.layoutManager =
            LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
//        binding.groupsRecyclerView.setHasFixedSize(true)
        listGroups = arrayListOf<GroupsModelInFragment>()
        getGoupsInfoData()
        binding.groupsRecyclerView.adapter =
            GroupsAdapterInFragment(contextActivity, listGroups)
    }

    fun getGoupsInfoData() {
        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_1,
                resources.getString(R.string.nice_places_group_name),
                "1K",
                "60", "Public"
            )
        )

        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_2,
                resources.getString(R.string.best_friends_group_name),
                "60",
                "2", "Private"
            )
        )
        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_3,
                resources.getString(R.string.professional_barbers_group_name),
                "60",
                "2", "Private"
            )
        )
        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_4,
                resources.getString(R.string.best_friends_group_name),
                "60",
                "2", "Private"
            )
        )
    }


}