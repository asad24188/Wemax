package com.wemax.mtech.Activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.chat.NewGroupChatActivity
import com.wemax.mtech.Adapter.groups.AddedFriendsAdapter
import com.wemax.mtech.Adapter.groups.MyGroupsAdapter
import com.wemax.mtech.Adapter.groups.SelectFriendsAdapter
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.Model.groups.SelectFriendsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivitySelectFriendsBinding

class SelectFriendsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectFriendsBinding
    val contextActivity = this@SelectFriendsActivity
    lateinit var utils: Utilities

    var listMyFriends: ArrayList<SelectFriendsModel> = arrayListOf()
    lateinit var adapter: MyGroupsAdapter

    var listAddedFriends: ArrayList<AddedFriendsModel> = arrayListOf()
    lateinit var adapterAddedFriends: AddedFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectFriendsBinding.inflate(layoutInflater)
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

        binding.newGroupChatAndAddedFriendsLayout.setOnClickListener {
            startActivity(Intent(contextActivity, NewGroupChatActivity::class.java))
        }

        binding.myFriendsRecycler.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.myFriendsRecycler.adapter = SelectFriendsAdapter(contextActivity, listMyFriends,layoutInflater)

        binding.addedFriendsRcv.layoutManager =
            LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
        initRecyclerViewAddedFriends()
        binding.addedFriendsRcv.adapter = AddedFriendsAdapter(contextActivity, listAddedFriends)


/*
        binding.newGroupChatAndAddedFriendsLayout.setOnClickListener {
            if (binding.addedFriendsRcv.visibility.equals(View.GONE)) {
                binding.addedFriendsRcv.visibility == View.VISIBLE
                binding.floatbtnLayout.visibility == View.VISIBLE
                binding.newGroupChatLayout.visibility == View.GONE
            } else {
                binding.addedFriendsRcv.visibility == View.GONE
                binding.floatbtnLayout.visibility == View.GONE
                binding.newGroupChatLayout.visibility == View.VISIBLE
            }
        }
*/

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

    private fun initRecyclerView() {

        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user1,
                false
            )
        )
        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user2,
                true
            )
        )
        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user3,
                false
            )
        )
        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user1,
                true
            )
        )

    }
}