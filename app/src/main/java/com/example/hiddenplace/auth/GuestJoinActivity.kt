package com.example.hiddenplace.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R
import com.example.hiddenplace.guest.GuestMainActivity

class GuestJoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_guest_join)

        val guestJoinAccept = findViewById<Button>(R.id.guestJoinAccept)

        guestJoinAccept.setOnClickListener {
            // GuideJoinActivity로 이동하는 인텐트 생성
            val intent = Intent(this, GuestMainActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }

    }
}