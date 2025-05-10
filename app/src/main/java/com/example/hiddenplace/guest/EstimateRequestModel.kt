package com.example.hiddenplace.guest

data class EstimateRequestModel(
    val guideId: Int,
    val travelerId: Int, //Int인지 Long인지 확인
    val age: Int,
    val gender: Boolean,
    val requestText: String,
    //val travelDate: Long, //Long인지 확인
    val isAccepted: String
)
