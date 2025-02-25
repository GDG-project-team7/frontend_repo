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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimate_list)

        recyclerView = findViewById(R.id.estimatelistRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 서버에서 데이터 가져오기
        fetchDataFromServer()
    }

    private fun fetchDataFromServer() {
        RetrofitClient.instance.getEstimates().enqueue(object : Callback<List<Estimate>> {
            override fun onResponse(call: Call<List<Estimate>>, response: Response<List<Estimate>>) {
                if (response.isSuccessful) {
                    val estimateItems = response.body() ?: emptyList()
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
                putExtra("userName", estimateItem.user.userName) // user 객체에서 가져오기
                putExtra("regionId", estimateItem.user.regionId)
                putExtra("age", estimateItem.age)
                putExtra("gender", estimateItem.gender)
                putExtra("text", estimateItem.text)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
