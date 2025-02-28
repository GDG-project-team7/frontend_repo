package com.example.hiddenplace.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R
import com.example.hiddenplace.guest.GuestMainActivity

class JoinSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_select)

        val guestSelectbtn = findViewById<Button>(R.id.guestSelectbtn) // 버튼 ID 가져오기

        guestSelectbtn.setOnClickListener {
            Toast.makeText(this,"여행자 로그인 성공!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GuestMainActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }


        /* 데모데이 이슈로 여행자 선택버튼 주석처리. 추후 주석 삭제 필요
        val guestSelectbtn = findViewById<Button>(R.id.guestSelectbtn)

        guestSelectbtn.setOnClickListener {
            val intent = Intent(this, GuestJoinActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }
        */


        val guideSelectbtn = findViewById<Button>(R.id.guideSelectbtn)

        guideSelectbtn.setOnClickListener {
            val intent = Intent(this, GuideJoinActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }


    }
}