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

        binding.editImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                // .compress(1024)			//Final image size will be less than 1 MB(Optional)
                //  .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        binding.arrowBack.setOnClickListener {
            finish()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            Toast.makeText(this, "Uploading...", Toast.LENGTH_SHORT).show()
            // Use Uri object instead of File to avoid storage permissions
            binding.profileImage.setImageURI(uri)
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}