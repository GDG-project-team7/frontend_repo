package com.example.hiddenplace.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GuideJoinService {
    @POST("/user/saveGuide")
    fun JoinUser(@Body joinModel: GuideJoinModel): Call<GuideJoinResponseModel>
}