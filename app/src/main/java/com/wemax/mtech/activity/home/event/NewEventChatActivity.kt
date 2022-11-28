package com.wemax.mtech.Activity.home.event

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.groups.AddedFriendsAdapter
import com.wemax.mtech.Adapter.groups.SelectFriendsAdapter
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.Model.groups.SelectFriendsModel
import com.wemax.mtech.R
import com.wemax.mtech.activity.home.event.EventDetailsActivity
import com.wemax.mtech.databinding.ActivityNewEventChatBinding
import com.wemax.mtech.databinding.ActivityNewGroupChatBinding

class NewEventChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewEventChatBinding
    var listAddedFriends: ArrayList<AddedFriendsModel> = arrayListOf()
    val contextActivity = this@NewEventChatActivity
    var listMyFriends: ArrayList<SelectFriendsModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewEventChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backpress.setOnClickListener {
            finish()
        }
        binding.btn.setOnClickListener {
            startActivity(Intent(this, EventDetailsActivity::class.java))
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        binding.addedFriendsRcv.layoutManager = LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
        initRecyclerViewAddedFriends()
        binding.addedFriendsRcv.adapter = AddedFriendsAdapter(contextActivity, listAddedFriends)


        binding.myFriendsRecycler.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.myFriendsRecycler.adapter = SelectFriendsAdapter(contextActivity, listMyFriends,layoutInflater)
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