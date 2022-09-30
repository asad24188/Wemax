package com.wemax.mtech.Activity.groups

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.NewReminderAdapter
import com.wemax.mtech.Adapter.groups.GroupPostsAdapterInFragment
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.Model.groups.SinglePostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityCreatePostBinding
import com.wemax.mtech.databinding.ActivityGroupPostsBinding
import java.util.ArrayList

class CreatePostActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreatePostBinding
    val contextActivity = this@CreatePostActivity
    lateinit var utils: Utilities
    var listGroupMembers: ArrayList<NewReminderModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }


    private fun initViews() {
        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.uploadEventImage.setOnClickListener {
            imagePicker()
        }
        binding.recyclerFriendshared.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerFriendshared.adapter = NewReminderAdapter(contextActivity, listGroupMembers)
    }
    private fun initRecyclerView() {
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john),
                false,
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive),
                true
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img1,
                resources.getString(R.string.william),
                false
            )
        )
    }
    private fun imagePicker() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(3024)            //Final image size will be less than 3 MB(Optional)
//            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            binding.eventImage.setImageURI(uri)
            binding.eventImage.visibility=View.VISIBLE
            binding.uploadEventImage.visibility=View.GONE
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}