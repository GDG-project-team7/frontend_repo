package com.example.hiddenplace.guide


data class Form(
    val formId: Int,
    val user: User,
    val age: Int,
    val gender: Boolean,
    val text: String,
    val TravelDate: Long
)

data class User(
    val userId: Int,
    val userName: String,
    val isGuide: Boolean,
    val region: String
)

