package com.example.hiddenplace

import com.example.hiddenplace.auth.GuestJoinService
import com.example.hiddenplace.auth.GuideJoinService
import com.example.hiddenplace.guest.AfterRegionPortService
import com.example.hiddenplace.guest.GuideListService
import com.example.hiddenplace.guide.EstimateListService
import com.example.hiddenplace.guide.SavePortfolioService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://43.201.53.241:8080" // 나중에 실제 URL로 변경

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    val instance: EstimateListService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EstimateListService::class.java)
    }

    val joinService: GuideJoinService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GuideJoinService::class.java)
    }

    val guestJoinService: GuestJoinService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GuestJoinService::class.java)
    }

    val guideListService : GuideListService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GuideListService::class.java)
    }

    val afterRegionPortService : AfterRegionPortService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AfterRegionPortService::class.java)
    }

    val savePortfolioService : SavePortfolioService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SavePortfolioService::class.java)
    }




}