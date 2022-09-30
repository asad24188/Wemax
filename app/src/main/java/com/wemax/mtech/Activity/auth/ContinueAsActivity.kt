package com.wemax.mtech.Activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityContinueAsBinding

class ContinueAsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContinueAsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinueAsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        click()
    }

    private fun click() {

        binding.individualLayout.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.businessLayout.setOnClickListener {
            startActivity(Intent(this, SignUpBusinessActivity::class.java))
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}