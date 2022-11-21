package com.wemax.mtech.Model.hobbies

import com.google.gson.annotations.SerializedName

data class HobbiesResponseModel(
    @SerializedName("status")
    var status : Boolean,

    @SerializedName("message")
    var message :String,

    @SerializedName("data")
    var data : HobbiesDataModel
)
