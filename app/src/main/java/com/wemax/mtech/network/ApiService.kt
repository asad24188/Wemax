package com.tabadol.tabadol.data.network


import com.google.gson.JsonObject
import com.wemax.mtech.Model.signup.SignUpResponseModel
import com.wemax.mtech.Model.verifydata.VerifyDataResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {



    @Headers("Accept: application/json")
    @POST("verify-data")
    @FormUrlEncoded
    fun verifyData(
        @Field("email") email: String,
        @Field("phone") phone: String?,
    ):
            Call<VerifyDataResponseModel>


    @Headers("Accept: application/json")
    @POST("signup")
    @FormUrlEncoded
    fun signUp(
        @Field("signup_as") signup_as: String,
        @Field("name") name: String?,
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("country_code") country_code: String?,
        @Field("phone") phone: String?,
        @Field("password") password: String?,
    ):
            Call<SignUpResponseModel>




}