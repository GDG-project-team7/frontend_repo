package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class AfterRegionPortActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_after_region_port)

        // 가이드를 선택하여 포트폴리오가 나온 후 견적서 작성하는 버튼 클릭 이벤트
        val WriteEstimateBtn : Button = findViewById(R.id.WriteEstimateBtn)
        WriteEstimateBtn.setOnClickListener{
            val intent = Intent(this, EstimateWriteActivity::class.java)
            startActivity(intent)
        }

    }
}