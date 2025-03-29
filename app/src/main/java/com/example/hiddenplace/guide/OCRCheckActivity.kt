package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R
import com.example.hiddenplace.auth.GuestJoinActivity
import com.example.hiddenplace.databinding.ActivityOcrcheckBinding

class OCRCheckActivity : AppCompatActivity() {

    private var userId: Int = 0

    private lateinit var binding: ActivityOcrcheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 초기화
        binding = ActivityOcrcheckBinding.inflate(layoutInflater)
        setContentView(binding.root) // binding.root로 레이아웃 설정
        enableEdgeToEdge()

        //userId가 Long 타입으로 저장된 경우 변환 후 사용
        userId = intent.extras?.get("userId")?.let {
            when (it) {
                is Int -> it // 이미 Int면 그대로 사용
                is Long -> it.toInt() // Long이면 Int로 변환
                is String -> it.toIntOrNull() ?: 0 // 혹시 String이면 변환 시도
                else -> 0 // 그 외는 0
            }
        } ?: 0

        Log.d("OCRActivity", "Intent extras: ${intent.extras}")  // Intent의 모든 데이터를 확인

        Log.d("OCRActivity", "Received userId: $userId")

        binding.OCRCheckBtn.setOnClickListener {
            val intent = Intent(this, WritePortfolioActivity::class.java)
            intent.putExtra("userId", userId) // userId 전달
            startActivity(intent)
        }


        // + 버튼 클릭 이벤트 (binding을 사용하여 OCRArea 클릭 처리)
        binding.OCRArea.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            // 이미지 선택 후 처리 코드 추가
        }
    }
}