package com.wemax.mtech.activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.activity.auth.BusinessOwnerActivity
import com.wemax.mtech.databinding.ActivityInviteFriends2Binding

class InviteFriends2Activity : AppCompatActivity() {
    lateinit var binding: ActivityInviteFriends2Binding
    val contextActivity = this@InviteFriends2Activity
    lateinit var utils: Utilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInviteFriends2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {

        binding.arrowBack.setOnClickListener { finish() }
/*        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }*/

        binding.arrowBack.setOnClickListener { finish() }
        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, BusinessOwnerActivity::class.java))
        }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, BusinessOwnerActivity::class.java))
        }
        /*binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, BusinessRequestsActivity::class.java)) }*/
    }
}