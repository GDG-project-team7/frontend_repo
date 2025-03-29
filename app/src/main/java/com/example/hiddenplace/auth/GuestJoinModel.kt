package com.example.hiddenplace.auth

data class GuestJoinModel(
    val userAccountName: String,
    val password: String,
    val userName: String,
    val phoneNumber: String,
    val birthday6Numbers: Int,
    val gender: Boolean,
    val email: String,
    val regionId: Int,
    val guide: Boolean,
    val form: String,
)
