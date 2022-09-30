package com.wemax.mtech.Model.groups

data class SinglePostModel(
    var userId: String,
    var postId: String,
    var groupId: String,
    var groupName: String,
    var groupImage: Int,
    var userName: String,
    var userImage: Int,
    var postImage: Int,
    var postPrivacyStatus: Int,
    var postTime: String,
    var postCaption: String,
    var postTotalLikes: String,
    var postTotalComments: String,
    var postTotalShares: String,

    )
