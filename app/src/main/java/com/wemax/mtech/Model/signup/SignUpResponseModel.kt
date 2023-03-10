package com.wemax.mtech.Model.signup

import com.google.gson.annotations.SerializedName

data class SignUpResponseModel(
    @SerializedName("status")
    var status : Boolean,

    @SerializedName("message")
    var message : String,

    @SerializedName("data")
    var data : SignUpDataModel
)
