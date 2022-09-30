package com.wemax.mtech.Model.groups

data class SelectFriendsModel(
/*
    var groupId: String,
    var groupProfileImage: Int,
    var groupName: String,
*/
    var userId: String,
    var userName: String,
    var userImage: Int,
    var userOnlineStatus: Boolean,
)
