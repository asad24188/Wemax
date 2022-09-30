package com.wemax.mtech.Activity.notification

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityShowMyProfileBinding

class ShowMyProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowMyProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShowMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        binding.arrowBack.setOnClickListener {
            finish()
        }

    }

}