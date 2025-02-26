package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AfterRegionPortActivity : AppCompatActivity() {


    private lateinit var mainTextView: TextView
    private lateinit var userNameTextView: TextView
    private lateinit var profileTextView: TextView
    private lateinit var travelSpotTextView: TextView
    private lateinit var foodSpotTextView: TextView
    private lateinit var photoSpotTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_region_port)

        // XML에서 ID 매핑
        mainTextView = findViewById(R.id.mainText)
        userNameTextView = findViewById(R.id.textView)
        profileTextView = findViewById(R.id.profileText)
        travelSpotTextView = findViewById(R.id.travelSpot)
        foodSpotTextView = findViewById(R.id.foodSpot)
        photoSpotTextView = findViewById(R.id.photoSpot)

        // Intent에서 `regionId`와 `id` 값을 가져옴
        val regionId = intent.getIntExtra("REGION_ID", 0)
        val guideId = intent.getIntExtra("GUIDE_ID", 0)  // 특정 가이드의 id


        Log.d("INTENT_DATA", "Received: GUIDE_ID=$guideId,  REGION_ID=$regionId")
        // API 요청하여 데이터 가져오기
        fetchRegionData(regionId, guideId)

        // 가이드 포트폴리오 확인 후 견적서 작성 페이지로 넘어가는 버튼 클릭이벤트
        val WriteEstimateBtn : Button = findViewById(R.id.WriteEstimateBtn)
        WriteEstimateBtn.setOnClickListener{
            val intent = Intent(this, EstimateWriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchRegionData(regionId: Int, guideId: Int) {
        Log.d("API_REQUEST", "Fetching data for regionId=$regionId, guideId=$guideId")

        // regionId를 전달하여 API 호출
        RetrofitClient.afterRegionPortService.getPortfolio(regionId).enqueue(object :
            Callback<List<AfterRegionPortmodel>> {
            override fun onResponse(
                call: Call<List<AfterRegionPortmodel>>,
                response: Response<List<AfterRegionPortmodel>>
            ) {
                if (response.isSuccessful) {
                    val regionGuides = response.body()
                    Log.d("API_SUCCESS", "Response received: $regionGuides")

                    if (!regionGuides.isNullOrEmpty()) {
                        // 특정 `id`에 해당하는 가이드 찾기
                        val guide = regionGuides.find { it.id == guideId }

                        if (guide != null) {
                            Log.d("DATA_FOUND", "Guide found: ${guide.userName}")

                            userNameTextView.text = guide.userName
                            mainTextView.text = guide.mainText
                            profileTextView.text = guide.profileText
                            travelSpotTextView.text = guide.travelSpot.joinToString(", ")
                            foodSpotTextView.text = guide.foodSpot.joinToString(", ")
                            photoSpotTextView.text = guide.photoSpot.joinToString(", ")

                            Log.d("API_SUCCESS", "Data loaded successfully for guideId: $guideId")
                        } else {
                            Log.e("DATA_ERROR", "Guide ID $guideId not found in region $regionId")
                        }
                    } else {
                        Log.e("DATA_ERROR", "No guides found for region ID $regionId")
                    }
                } else {
                    Log.e("API_ERROR", "Response failed: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<AfterRegionPortmodel>>, t: Throwable) {
                Log.e("API_FAILURE", "API request failed: ${t.message}", t)
                Toast.makeText(this@AfterRegionPortActivity, "데이터 로딩 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }




}