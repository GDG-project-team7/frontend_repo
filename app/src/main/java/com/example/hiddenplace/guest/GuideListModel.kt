package com.example.hiddenplace.guest

data class GuideListModel(
    val userId : Int,
    val userName: String,   // 가이드 이름
    val introduction: String, // 가이드 소개
    val regionId: Int
)
