package com.example.hiddenplace.guide

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface EstimateListService {
    @GET("/guidePage/{guideId}/getForms")
    fun getEstimates(@Path("guideId") guideId: Int): Call<List<Estimate>>
}