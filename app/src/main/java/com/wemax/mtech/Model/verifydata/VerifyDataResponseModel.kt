package com.wemax.mtech.Model.verifydata

import com.google.gson.annotations.SerializedName

data class VerifyDataResponseModel(
    @SerializedName("status")
    var status : Boolean,

    @SerializedName("message")
    var message : String,

    @SerializedName("data")
    var data : VerifyDataModel
)
