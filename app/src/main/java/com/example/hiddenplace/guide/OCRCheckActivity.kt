package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R
import com.example.hiddenplace.auth.GuestJoinActivity
import com.example.hiddenplace.databinding.ActivityOcrcheckBinding

class OCRCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOcrcheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ocrcheck)

        //포트폴리오 작성 페이지로 넘어가기
        val OCRCheckBtn = findViewById<Button>(R.id.OCRCheckBtn)

        OCRCheckBtn.setOnClickListener {
            val intent = Intent(this, WritePortfolioActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }

        //  + 버튼 클릭 이벤트
        binding.OCRArea.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100) {
        }
    }


}