package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import com.example.hiddenplace.RetrofitClient.guideListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectGuideActivity : AppCompatActivity()  {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GuideListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_guide)

        recyclerView = findViewById(R.id.GuideListRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GuideListAdapter { guide ->
            Log.d("CLICK_EVENT", "Guide clicked: ID=${guide.id}, Name=${guide.userName}, Region=${guide.regionId}")
            val intent = Intent(this, AfterRegionPortActivity::class.java)
            intent.putExtra("GUIDE_ID", guide.id)  // 올바른 키 설정
            intent.putExtra("USER_NAME", guide.userName)
            intent.putExtra("REGION_ID", guide.regionId) // 지역 정보 전달
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val regionId = intent.getIntExtra("regionId", -1)
        if (regionId != -1) {
            fetchGuides(regionId)
        }

    }

    private fun fetchGuides(regionId: Int) {
        Log.d("API_CALL", "Fetching users from server...")
        RetrofitClient.guideListService.getGuides(regionId).enqueue(object : Callback<List<GuideListModel>> {
            override fun onResponse(call: Call<List<GuideListModel>>, response: Response<List<GuideListModel>>) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body() ?: emptyList())

                    val guides = response.body()
                    Log.d("API_RESPONSE", "Received guides: $guides") // 응답 데이터 로그 추가
                }
            }

            override fun onFailure(call: Call<List<GuideListModel>>, t: Throwable) {
                Log.e("API_FAILURE", "API request failed: ${t.message}", t)
                Toast.makeText(this@SelectGuideActivity, "데이터 로딩 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }



}