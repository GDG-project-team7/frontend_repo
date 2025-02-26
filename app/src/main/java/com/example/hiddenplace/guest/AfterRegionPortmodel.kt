package com.example.hiddenplace.guest

data class AfterRegionPortmodel(
    val id: Int,
    val userName: String,
    val mainText: String,
    val profileText: String,
    val travelSpot: List<String>,
    val foodSpot: List<String>,
    val photoSpot: List<String>,
    val regionId: Int
)
