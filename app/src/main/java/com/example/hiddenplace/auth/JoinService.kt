package com.example.hiddenplace.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JoinService {
    @POST("/api/register")
    fun JoinUser(@Body joinModel: JoinModel): Call<JoinResponseModel>
}