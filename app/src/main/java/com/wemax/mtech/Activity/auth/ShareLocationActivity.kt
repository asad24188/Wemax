package com.wemax.mtech.Activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.databinding.ActivityShareLocationBinding

class ShareLocationActivity : AppCompatActivity() {
    lateinit var binding: ActivityShareLocationBinding
    val contextActivity = this@ShareLocationActivity
    lateinit var utils: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareLocationBinding.inflate(layoutInflater)
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
            startActivity(Intent(this, HobbiesInterestActivity::class.java)) }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, HobbiesInterestActivity::class.java)) }
    }
}