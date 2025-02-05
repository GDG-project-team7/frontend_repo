package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class EstimateCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estimate_check)

        // 전달받은 데이터 가져오기
        val userName = intent.getStringExtra("userName")
        val region = intent.getStringExtra("region")
        val age = intent.getIntExtra("age", -1) // 기본값 -1
        val gender = intent.getBooleanExtra("gender", false) // 기본값 false
        val requestText = intent.getStringExtra("text") // 요청사항

        // 화면에 데이터 설정
        val ageTextView: TextView = findViewById(R.id.age_area)
        val sexTextView: TextView = findViewById(R.id.sex_area)
        val textAreaView: TextView = findViewById(R.id.text_area)

        // 나이 표시
        ageTextView.text = "나이: $age"

        // 성별 표시
        sexTextView.text = if (gender) "성별: 남성" else "성별: 여성"

        // 나머지 데이터 (이름, 지역, 요청사항) 표시
        val additionalInfo = """
            이름: $userName
            지역: $region
            요청사항:
            $requestText
        """.trimIndent()

        textAreaView.text = additionalInfo



        // 수락 버튼
        val acceptanceButton = findViewById<Button>(R.id.acceptance_button)
        acceptanceButton.setOnClickListener {
            // SendPortfolioActivity로 이동
            val intent = Intent(this, SendPortfolioActivity::class.java)
            startActivity(intent)
        }

        // 거절 버튼
        val rejectionButton = findViewById<Button>(R.id.rejection_button)
        rejectionButton.setOnClickListener {
            // EstimateRejectionActivity로 이동
            val intent = Intent(this, EstimateRejectionActivity::class.java)
            startActivity(intent)
        }

    }
}




