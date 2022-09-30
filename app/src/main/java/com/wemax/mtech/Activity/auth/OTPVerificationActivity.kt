package com.wemax.mtech.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityOtpverificationBinding

class OTPVerificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpverificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        click()
    }

    private fun click() {
        binding.arrowBack.setOnClickListener { finish() }
        binding.btnVerify.setOnClickListener {
            startActivity(Intent(this, CompleteProfileActivity::class.java))
        }
    }
}