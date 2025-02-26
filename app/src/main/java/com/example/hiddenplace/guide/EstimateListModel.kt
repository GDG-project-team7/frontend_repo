package com.example.hiddenplace.guide

data class User (
    val userId: String,
    val userName: String,
    val isGuide: Boolean,
    val regionId: Int
)

data class Estimate (
    val formId: Int,
    val user: User,
    val age: Int,
    val gender: Boolean,
    val text: String,
    val travelDate: Long
)

