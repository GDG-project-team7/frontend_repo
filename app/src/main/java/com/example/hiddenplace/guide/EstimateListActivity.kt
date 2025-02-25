package com.example.hiddenplace.guide

import android.os.Bundle
import android.view.View
import android.view.WindowInsetsAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import com.example.hiddenplace.databinding.ActivityEstimateListBinding
import retrofit2.Call
import retrofit2.Response

class EstimateListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEstimateListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstimateListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_estimate_list)

        binding.estimatelistRV.layoutManager = LinearLayoutManager(this)

        loadEstimates()
    }

    private fun loadEstimates() {

        RetrofitClient.instance.getEstimates().enqueue(object : retrofit2.Callback<List<Estimate>> {
            override fun onResponse(call: Call<List<Estimate>>, response: Response<List<Estimate>>) {
                if (response.isSuccessful) {
                    val estimates = response.body() ?: emptyList()
                    if (estimates.isEmpty()) {
                        binding.emptyTextView.visibility = View.VISIBLE  // "견적서 없음" 메시지 표시
                    } else {
                        binding.estimatelistRV.adapter = EstimateListRVAdapter(estimates)
                        binding.emptyTextView.visibility = View.GONE
                    }
                    Toast.makeText(this@EstimateListActivity, "데이터 수신 성공!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@EstimateListActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Estimate>>, t: Throwable) {
                Toast.makeText(this@EstimateListActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}