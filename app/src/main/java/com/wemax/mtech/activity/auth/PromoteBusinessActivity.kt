package com.wemax.mtech.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wemax.mtech.databinding.ActivityPromoteBusinessBinding

class PromoteBusinessActivity : AppCompatActivity() {
    lateinit var binding:ActivityPromoteBusinessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPromoteBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clicks()
    }

    private fun clicks() {
        binding.arrowBack.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(0,0)
        }
        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(0,0)
        }
    }
}