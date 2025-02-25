package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class GuestCheckPortActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest_check_port)

        // 포트폴리오 보고 확인 버튼 누르면 게스트 메인페이지로 이동하도록 버튼 클릭 이벤트
        val GuestCheckPortBtn : Button = findViewById(R.id.GuestCheckPortBtn)
        GuestCheckPortBtn.setOnClickListener{
            val intent = Intent(this, GuestMainActivity::class.java)
            startActivity(intent)
        }
    }
}