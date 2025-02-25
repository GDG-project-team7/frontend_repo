package com.example.hiddenplace.guide

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface EstimateListService {
    @GET("Test/getGuideList")// 나중에 실제 URL을 넣어야 함
    fun getEstimates(): Call<List<Estimate>>
}