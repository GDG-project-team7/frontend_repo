package com.example.hiddenplace.guest

data class GuideListModel(
    val id : Int,
    val userName: String,   // 가이드 이름
    val profileText: String, // 가이드 소개
    val regionId: Int
)
