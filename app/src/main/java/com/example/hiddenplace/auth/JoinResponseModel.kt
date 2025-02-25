package com.example.hiddenplace.auth

data class JoinResponseModel(
    val userId: String,
    val userName: String,
    val email: String,
    val isGuide: Boolean
)
