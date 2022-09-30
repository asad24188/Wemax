package com.wemax.mtech.Activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityBusinessOwnerBinding

class BusinessOwnerActivity : AppCompatActivity() {
    lateinit var binding: ActivityBusinessOwnerBinding
    val contextActivity = this@BusinessOwnerActivity
    lateinit var utils: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.arrowBack.setOnClickListener { finish() }
        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, PromoteBusinessActivity::class.java))
        }
        binding.btnNo.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}