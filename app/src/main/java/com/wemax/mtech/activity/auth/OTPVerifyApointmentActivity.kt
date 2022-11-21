package com.wemax.mtech.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityOtpverifyApointmentBinding

class OTPVerifyApointmentActivity : AppCompatActivity() {

    lateinit var binding: ActivityOtpverifyApointmentBinding
    val contextActivity = this@OTPVerifyApointmentActivity
    private val SPLASH_TIME_OUT = 8000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverifyApointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        binding.arrowBack.setOnClickListener { finish() }
        binding.btnVerify.setOnClickListener {
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