package com.example.hiddenplace.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R

class JoinSelectActivity : AppCompatActivity() {

    private var isGuide: Boolean = false // 기본값은 false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_select)

        val btnGuide = findViewById<Button>(R.id.guideSelectbtn)
        val btnGuest = findViewById<Button>(R.id.guestSelectbtn)

        btnGuide.setOnClickListener {
            isGuide = true // 가이드 선택 시 isGuide 값을 true로 설정
            navigateToNextPage()
        }

        btnGuest.setOnClickListener {
            isGuide = false // 게스트 선택 시 isGuide 값을 false로 설정
            navigateToNextPage()
        }

    }

    // 다음 페이지로 이동 (isGuide 값에 따라 이동할 액티비티 다르게 설정)
    private fun navigateToNextPage() {
        val intent = if (isGuide) {
            Intent(this, GuideJoinActivity::class.java) // 가이드 선택 시 GuideJoinActivity
        } else {
            Intent(this, GuestJoinActivity::class.java) // 게스트 선택 시 GuestJoinActivity
        }

        intent.putExtra("isGuide", isGuide) // isGuide 값을 다음 페이지로 전달
        startActivity(intent)
    }
}