package com.wemax.mtech.Model.signup

import com.google.gson.annotations.SerializedName

data class SignUpDataModel(
    @SerializedName("id")
    var id : Int,

    @SerializedName("google_id")
    var google_id : String,

    @SerializedName("facebook_id")
    var facebook_id : String,

    @SerializedName("fingerprint_id")
    var fingerprint_id : String,

    @SerializedName("name")
    var name : String,

    @SerializedName("username")
    var username :String,

    @SerializedName("business_name")
    var business_name :String,

    @SerializedName("email")
    var email : String,

    @SerializedName("country_code")
    var country_code :String,

    @SerializedName("phone")
    var phone :String,

    @SerializedName("profile_image")
    var profile_image :String,

    @SerializedName("about")
    var about : String? = null,

    @SerializedName("invitation_url")
    var invitation_url : String?=null,

    @SerializedName("hobbies")
    var hobbies :String?=null,

    @SerializedName("latitude")
    var latitude :String,

    @SerializedName("longitude")
    var longitude : String,

    @SerializedName("type")
    var type : String
)
