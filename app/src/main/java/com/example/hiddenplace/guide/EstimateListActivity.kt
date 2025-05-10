package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstimateListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EstimateListRVAdapter

    private var guideId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimate_list)

        // guideId를 임시로 40으로 설정
        //guideId = 40
        //Log.d("EstimateListActivity", "Testing with guideId: $guideId")
        guideId = intent.getIntExtra("GUIDE_ID",0)
        recyclerView = findViewById(R.id.estimatelistRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 서버에서 데이터 가져오기
        fetchDataFromServer()
    }

    private fun fetchDataFromServer() {
        RetrofitClient.estimateListService.getEstimates(guideId).enqueue(object : Callback<List<Estimate>> {
            override fun onResponse(call: Call<List<Estimate>>, response: Response<List<Estimate>>) {
                if (response.isSuccessful) {
                    val estimateItems = response.body() ?: emptyList()
                    Log.d("EstimateListActivity", "Received Estimates: $estimateItems")
                    setupRecyclerView(estimateItems)
                } else {
                    Log.e("API_ERROR", "Response Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Estimate>>, t: Throwable) {
                Log.e("API_ERROR", "Network Error: ${t.message}")
            }
        })
    }

    private fun setupRecyclerView(estimateItems: List<Estimate>) {
        adapter = EstimateListRVAdapter(estimateItems) { estimateItem ->
            val intent = Intent(this, EstimateCheckActivity::class.java).apply {
                putExtra("userName", estimateItem.traveler.userName) // traveler 객체에서 가져오기
                putExtra("regionId", estimateItem.traveler.regionId)
                putExtra("age", estimateItem.age)
                putExtra("gender", estimateItem.gender)
                putExtra("text", estimateItem.requestText)
                putExtra("IS_ACCEPTED", estimateItem.isAccepted)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
