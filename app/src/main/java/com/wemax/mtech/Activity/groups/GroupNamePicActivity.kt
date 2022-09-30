package com.wemax.mtech.Activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.chat.ChatActivity
import com.wemax.mtech.Activity.chat.GroupChatActivity
import com.wemax.mtech.Adapter.groups.AddedFriendsAdapter
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityGroupNamePicBinding

class GroupNamePicActivity : AppCompatActivity() {
    lateinit var binding: ActivityGroupNamePicBinding
    val contextActivity = this@GroupNamePicActivity
    lateinit var utils: Utilities
    var listAddedFriends: ArrayList<AddedFriendsModel> = arrayListOf()
    lateinit var adapterAddedFriends: AddedFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupNamePicBinding.inflate(layoutInflater)
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

        binding.addedFriendsRcv.layoutManager = LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
        initRecyclerViewAddedFriends()
        binding.addedFriendsRcv.adapter = AddedFriendsAdapter(contextActivity, listAddedFriends)

        binding.floatbtnLayout.setOnClickListener {
            startActivity(Intent(contextActivity, GroupChatActivity::class.java))
            finish()
        }



    }

    private fun initRecyclerViewAddedFriends() {

        listAddedFriends.add(
            AddedFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user1,
            )
        )
        listAddedFriends.add(
            AddedFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user2,
            )
        )
        listAddedFriends.add(
            AddedFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user3,
            )
        )
        listAddedFriends.add(
            AddedFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user1,
            )
        )

    }

}