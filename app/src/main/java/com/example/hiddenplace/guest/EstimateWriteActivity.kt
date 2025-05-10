package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

class EstimateWriteActivity : AppCompatActivity() {

    private var guideId: Int = 0
    private var travelerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estimate_write)

        guideId = intent.getIntExtra("GUIDE_ID", 0)
        travelerId = intent.getIntExtra("TRAVELER_ID", 0)

        val ageEditText: EditText = findViewById(R.id.age_area)
        val genderEditText: EditText = findViewById(R.id.sex_area)
        val requestTextEditText: EditText = findViewById(R.id.editText)
        val estimateAcceptBtn: Button = findViewById(R.id.EstimateAcceptBtn)

        estimateAcceptBtn.setOnClickListener{

            val age = ageEditText.text.toString().toIntOrNull() ?: 0
            val gender = genderEditText.text.toString() == "M"
            val requestText = requestTextEditText.text.toString()
            //val travelDate = System.currentTimeMillis()

            val estimateRequest = EstimateRequestModel(
                guideId = guideId,
                travelerId = travelerId,
                age = age,
                gender = gender,
                requestText = requestText,
                //travelDate = travelDate,
                isAccepted = "pending"
            )

            sendEstimate(estimateRequest)

        }
    }
    private fun sendEstimate(estimateRequest: EstimateRequestModel) {
        val service = RetrofitClient.estimateService
        service.sendEstimate(estimateRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EstimateWriteActivity, "견적서가 성공적으로 전송되었습니다", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@EstimateWriteActivity, GuestMainActivity::class.java))
                } else {
                    Toast.makeText(this@EstimateWriteActivity, "서버 응답 실패", Toast.LENGTH_SHORT).show()
                    Log.e("EstimateError", "Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@EstimateWriteActivity, "네트워크 오류 발생", Toast.LENGTH_SHORT).show()
                Log.e("EstimateError", "Network Error: ${t.message}")
            }
        })
    }
}