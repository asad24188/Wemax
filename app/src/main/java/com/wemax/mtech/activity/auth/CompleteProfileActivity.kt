package com.wemax.mtech.activity.auth

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.dhaval2404.imagepicker.ImagePicker
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.databinding.ActivityCompleteProfileBinding
import com.wemax.mtech.utils.Constants
import java.io.File

class CompleteProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityCompleteProfileBinding
    private lateinit var utilities: Utilities
    private var imagefile: File? = null
    private var description = ""
    private var uri: Uri? = null
    private var imageUploaded: Boolean = false
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
            description = binding.groupDescriptionEt.text.toString()
            if (description == "") {
                utilities.showFailureToast(this@CompleteProfileActivity, "Please Enter Description")
            } else if (!imageUploaded) {
                utilities.showFailureToast(
                    this@CompleteProfileActivity,
                    "Please Enter Profile Image"
                )
            } else {
                val intentShareLocation =
                    Intent(this@CompleteProfileActivity, ShareLocationActivity::class.java)
                Constants.image = uri.toString()
                Constants.isUploaded = "yes"
                Constants.description = description
                startActivity(intentShareLocation)
            }

        }
        binding.tvSkip.setOnClickListener {
            val intentShareLocation =
                Intent(this@CompleteProfileActivity, ShareLocationActivity::class.java)
            Constants.isUploaded = "no"
            startActivity(intentShareLocation)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            uri = data?.data!!
            binding.profileImage.setImageURI(uri)
            val photoFile = File(uri!!.path!!)
            imagefile = photoFile
            imageUploaded = true


        }
    }

}