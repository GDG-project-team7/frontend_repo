package com.example.hiddenplace.guide


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface SavePortfolioService {
    @POST("/guidePage/savePortfolio/{guideId}")
    fun SavePort(@Path("guideId") guideId: Int, @Body savePortfolioModel: SavePortfolioModel): Call<savePortResponseModel>
}