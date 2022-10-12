package com.wemax.mtech.Model.verifydata

import com.google.gson.annotations.SerializedName

data class VerifyDataModel(
    @SerializedName("country_code")
    var country_code : String? = null,

    @SerializedName("phone")
    var phone : String,

    @SerializedName("otp")
    var otp : String
)
