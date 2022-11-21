package com.wemax.mtech.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var binding:ActivityForgetPasswordBinding
    val contextActivity = this@ForgetPasswordActivity
    lateinit var utils: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
    }
}