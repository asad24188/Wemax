package com.wemax.mtech.activity.home.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wemax.mtech.databinding.ActivityAddYourOwn2Binding

class AddYourOwnActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityAddYourOwn2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddYourOwn2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        onClicks()
    }
    private fun onClicks() {
        binding.backpress.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
        }
    }
}