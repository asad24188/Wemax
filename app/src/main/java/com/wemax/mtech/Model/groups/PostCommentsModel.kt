package com.wemax.mtech.Model.groups

data class PostCommentsModel(
    var postId: String,
    var userId: String,
    var userName: String,
    var userImage: Int,
    var userComment: String,
    var commentTime: String,
)
