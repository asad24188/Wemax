package com.wemax.mtech.Model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.io.File

data class SignupInformationModel(
    var isUploaded : String,
    var description : String,
    var image : String? = null
){
    lateinit var isUpload : String
}
