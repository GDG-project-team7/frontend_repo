package com.example.hiddenplace.guest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AfterRegionPortService {
    @GET("{regionId}") // {regionId}를 URL에 포함
    fun getPortfolio(@Path("regionId") regionId: Int): Call<List<AfterRegionPortmodel>>
}