package com.wemax.mtech.Activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.CompleteProfileActivity
import com.wemax.mtech.Activity.OTPVerificationActivity
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityTouchVerificationBinding

class TouchVerificationActivity : AppCompatActivity() {
    lateinit var binding:ActivityTouchVerificationBinding
    private lateinit var utilities: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTouchVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        clicks()
    }

    private fun initViews() {
        utilities = Utilities(this@TouchVerificationActivity)
        utilities.setWhiteBars(this@TouchVerificationActivity)
    }

    private fun clicks() {
        binding.arrowBack.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
        binding.phoneVerify.setOnClickListener {
            startActivity(Intent(this,OTPVerificationActivity::class.java))
            overridePendingTransition(0,0)
        }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this,CompleteProfileActivity::class.java))
            overridePendingTransition(0,0)
        }
    }
}