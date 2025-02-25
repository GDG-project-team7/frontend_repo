package com.example.hiddenplace.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R

class JoinSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_select)

        val guestSelectbtn = findViewById<Button>(R.id.guestSelectbtn)

        guestSelectbtn.setOnClickListener {
            val intent = Intent(this, GuestJoinActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }

        val guideSelectbtn = findViewById<Button>(R.id.guideSelectbtn)

        guideSelectbtn.setOnClickListener {
            val intent = Intent(this, GuideJoinActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }


    }
}