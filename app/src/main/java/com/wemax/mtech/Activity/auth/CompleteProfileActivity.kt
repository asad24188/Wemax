package com.wemax.mtech.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.imageview.ShapeableImageView
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.auth.HomeActivity
import com.wemax.mtech.Activity.auth.ShareLocationActivity
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityCompleteProfileBinding

class CompleteProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityCompleteProfileBinding
    private lateinit var utilities: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompleteProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
        click()




    }

    private fun initViews() {
        utilities = Utilities(this@CompleteProfileActivity)
        utilities.setWhiteBars(this@CompleteProfileActivity)
    }

    private fun click() {
        binding.profileImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
        binding.arrowBack.setOnClickListener { finish() }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, ShareLocationActivity::class.java))
//            finish()
        }
        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, ShareLocationActivity::class.java))
            finish()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE){
            binding.profileImage.setImageURI(data?.data)
//            selected_img_bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)
//            picimage?.setImageBitmap(selected_img_bitmap)

            Log.d("imageUri", data.toString())

        }
    }
}