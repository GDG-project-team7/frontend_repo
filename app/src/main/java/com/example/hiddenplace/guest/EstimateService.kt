package com.example.hiddenplace.guest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EstimateService {
    @POST("/matching/select-guide")
    fun sendEstimate(
        @Body estimateRequest: EstimateRequestModel
    ): Call<Void>
}