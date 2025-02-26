package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class ViewMyEstimateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_my_estimate)

        // 견적서를 보낸 가이드의 포트폴리오 보기 버튼 클릭 이벤트
        val reviewPortBtn : Button = findViewById(R.id.reviewPortBtn)
        reviewPortBtn.setOnClickListener{
            val intent = Intent(this, GuestCheckPortActivity::class.java)
            startActivity(intent)
        }
    }
}