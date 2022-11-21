package com.wemax.mtech.activity.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.groups.AddedFriendsAdapter
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    val contextActivity = this@ChatActivity
    lateinit var utils: Utilities

    var listAddedFriends: ArrayList<AddedFriendsModel> = arrayListOf()
    lateinit var adapterAddedFriends: AddedFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_gray)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.backpress1.setOnClickListener { finish() }
    }
}