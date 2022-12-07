package com.wemax.mtech.Model.homeApi

data class NailSalonsAroundMe(
    val business_category_id: String,
    val distance: Int,
    val id: Int,
    val price_currency: String,
    val service_price: String,
    val service_time: String,
    val service_title: String,
    val subcategory_id: String,
    val subcategory_image: String,
    val subcategory_name: String,
    val user_id: String
)