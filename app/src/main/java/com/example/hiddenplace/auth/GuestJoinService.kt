package com.example.hiddenplace.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GuestJoinService {
    @POST("/user/saveTraveler")
    fun GuestJoinUser(@Body guestJoinModel: GuestJoinModel): Call<GuestJoinResponseModel>
}

