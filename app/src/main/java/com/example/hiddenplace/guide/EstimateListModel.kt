package com.example.hiddenplace.guide

data class User (
    val userId: Int,
    val userName: String,
    val regionId: Int,
)

data class Estimate (
    val guide: User,
    val traveler: User,
    val age: Int,
    val gender: Boolean,
    val requestText: String,
    //val travelDate: Long,
    val isAccepted: String
)


