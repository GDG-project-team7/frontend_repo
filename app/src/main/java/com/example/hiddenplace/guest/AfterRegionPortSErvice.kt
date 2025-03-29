package com.example.hiddenplace.guest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AfterRegionPortService {
    @GET("/travelerPage/getPortfolio/{guideId}")
    fun getPortfolio(@Path("guideId") guideId: Int): Call<List<AfterRegionPortmodel>>
}