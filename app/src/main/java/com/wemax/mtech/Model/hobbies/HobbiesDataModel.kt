package com.wemax.mtech.Model.hobbies

import com.google.gson.annotations.SerializedName

data class HobbiesDataModel(
    @SerializedName("id")
    var id : Int,

    @SerializedName("hobby")
    var hobby : String
)
