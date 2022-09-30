package com.wemax.mtech.Model.serviceDetailsModel

data class ServicesReviewsModel(
    var userId: String,
    var userImage: Int,
    var userName: String,
    var commentDateAndTime: String,
    var commentText: String,
    var ratingStarValue: String
)