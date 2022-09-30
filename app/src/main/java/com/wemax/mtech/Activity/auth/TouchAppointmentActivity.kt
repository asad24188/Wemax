package com.wemax.mtech.Activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import com.wemax.mtech.Activity.CompleteProfileActivity
import com.wemax.mtech.Activity.OTPVerificationActivity
import com.wemax.mtech.Activity.OTPVerifyApointmentActivity
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityTouchAppointmentBinding

class TouchAppointmentActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 8000
    lateinit var binding:ActivityTouchAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTouchAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clicks()
    }

    private fun clicks() {
        binding.arrowBack.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
        binding.phoneVerify.setOnClickListener {
            startActivity(Intent(this, OTPVerifyApointmentActivity::class.java))
            overridePendingTransition(0,0)
        }
        binding.btnContinue.setOnClickListener {
            getDataDialog()
        }
    }
    private fun getDataDialog() {

        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_congrates, null)

        Handler().postDelayed({
            builder.dismiss()
        }, SPLASH_TIME_OUT.toLong())

        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}