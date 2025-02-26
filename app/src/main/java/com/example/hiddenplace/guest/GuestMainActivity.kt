package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class GuestMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest_main)

        // 작성한 견적서 보기 버튼 클릭 이벤트
        val lookRequestBtn : Button = findViewById(R.id.lookRequestBtn)
        lookRequestBtn.setOnClickListener{
            val intent = Intent(this, ViewMyEstimateActivity::class.java)
            startActivity(intent)
        }

    }
}