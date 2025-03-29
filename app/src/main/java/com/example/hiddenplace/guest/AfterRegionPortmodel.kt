package com.example.hiddenplace.guest

data class AfterRegionPortmodel(
    val id: Int,
    val userName: String,
    val title: String,
    val introduction: String,
    val travelPlace: List<String>,
    val foodPlace: List<String>,
    val photoPlace: List<String>,
    // val regionId: Int
)
