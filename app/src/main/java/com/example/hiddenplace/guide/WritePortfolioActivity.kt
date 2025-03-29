package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WritePortfolioActivity : AppCompatActivity() {
    private var userId: Int = 0 //userId 저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_portfolio)

        userId = if (intent.hasExtra("userId")) {
            intent.getIntExtra("userId", 0)
        } else {
            Log.e("WritePortfolioActivity", "Intent does not contain userId")
            0
        }

        Log.d("WritePortfolioActivity", "Received userId: $userId")

        val writePortfolioBtn = findViewById<Button>(R.id.writePortfolioBtn)
        val saveIntroduction = findViewById<EditText>(R.id.saveIntroduction)
        val saveTitle = findViewById<EditText>(R.id.saveTitle)
        val saveTravelPlace = findViewById<EditText>(R.id.saveTravelPlace)
        val saveFoodPlace = findViewById<EditText>(R.id.saveFoodPlace)
        val savePhotoPlace = findViewById<EditText>(R.id.savePhotoPlace)

        writePortfolioBtn.setOnClickListener {
            val Title = saveTitle.text.toString()
            val Introduction = saveIntroduction.text.toString()
            val TravelPlace = saveTravelPlace.text.toString()
            val FoodPlace = saveFoodPlace.text.toString()
            val PhotoPlace = savePhotoPlace.text.toString()

            val savePortfolioModel = SavePortfolioModel(
                id = userId,
                title = Title,
                introduction = Introduction,
                travelPlace = TravelPlace,
                foodPlace = FoodPlace,
                photoPlace = PhotoPlace
            )
            savePortfolioRequest(userId,savePortfolioModel)
        }
    }

    private fun savePortfolioRequest(guideId:Int, savePortfolioModel: SavePortfolioModel) {

        // 1. Gson 객체 생성: JSON 변환을 위해 Gson 사용
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonBody = gson.toJson(savePortfolioModel)  // 모델을 JSON 형식으로 변환

        // 2. 서버 요청 전 JSON 로그 출력
        Log.d("WritePortfolioActivity", "서버 요청 JSON: $jsonBody") // JSON 데이터 확인

        RetrofitClient.savePortfolioService.SavePort(guideId, savePortfolioModel).enqueue(object : Callback<savePortResponseModel> {
            override fun onResponse(call: Call<savePortResponseModel>, response: Response<savePortResponseModel>) {
                Log.d("WritePortfolioActivity", "서버 응답 코드: ${response.code()}")
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("WritePortfolioActivity", "저장 성공: ${responseData}")


                    responseData?.let {
                        Toast.makeText(this@WritePortfolioActivity, "성공", Toast.LENGTH_SHORT).show()

                        // **회원가입 성공 후 페이지 이동**
                        val intent = Intent(this@WritePortfolioActivity, GuideMainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                } else {

                    val errorBody = response.errorBody()?.string()
                    Log.e("WritePortfolioActivity", "저장 실패 - 응답 코드: ${response.code()}")
                    Log.e("WritePortfolioActivity", "저장 실패 - 에러 메시지: $errorBody")

                    Toast.makeText(this@WritePortfolioActivity, "실패 ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<savePortResponseModel>, t: Throwable) {
                Log.e("WritePortfolioActivity", "서버 요청 실패: ${t.message}")

                Toast.makeText(this@WritePortfolioActivity, "서버 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}