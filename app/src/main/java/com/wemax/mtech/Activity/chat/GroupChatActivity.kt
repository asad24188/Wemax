package com.wemax.mtech.Activity.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wemax.mtech.Activity.groups.GroupInfoActivity
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityGroupChatBinding

class GroupChatActivity : AppCompatActivity() {
    private lateinit var binding:ActivityGroupChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGroupChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        binding.groupSettings.setOnClickListener {
            startActivity(Intent(this,GroupInfoActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        binding.backpress1.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
    }
}