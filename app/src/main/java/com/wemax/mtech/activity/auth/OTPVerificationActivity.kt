package com.wemax.mtech.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.databinding.ActivityOtpverificationBinding

class OTPVerificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpverificationBinding
    private var otp = ""
    var name = ""
    private var fullName = ""
    private var countryCode = ""
    var phone = ""
    private var email = ""
    private var password = ""
    private lateinit var utilities: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        click()
    }

    private fun initViews() {
        utilities = Utilities(this@OTPVerificationActivity)
        val intent = intent
        otp = intent.getStringExtra("otp").toString()
        name =intent.getStringExtra("name").toString()
        fullName = intent.getStringExtra("fullname").toString()
        countryCode = intent.getStringExtra("code").toString()
        phone = intent.getStringExtra("phone").toString()
        email = intent.getStringExtra("email").toString()
        password = intent.getStringExtra("password").toString()


    }

    private fun click() {
        binding.arrowBack.setOnClickListener { finish() }
        binding.btnVerify.setOnClickListener {

        }
    }


}