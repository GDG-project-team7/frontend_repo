package com.example.hiddenplace

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.auth.GuideJoinActivity
import com.example.hiddenplace.auth.JoinSelectActivity
import com.example.hiddenplace.auth.LoginActivity
import com.example.hiddenplace.guide.EstimateListActivity
import com.example.hiddenplace.guide.GuideMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //회원가입 버튼 클릭 시 페이지 이동
        val joinLoginbtn = findViewById<Button>(R.id.joinbtn) // 버튼 ID 가져오기

        joinLoginbtn.setOnClickListener {
            // GuideJoinActivity로 이동하는 인텐트 생성
            val intent = Intent(this, JoinSelectActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }

        //로그인 버튼 클릭 시 페이지 이동
        val Loginbtn = findViewById<Button>(R.id.Loginbtn) // 버튼 ID 가져오기

        Loginbtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }

        //지도 버튼 클릭 시 페이지 이동
        val mapbtn = findViewById<Button>(R.id.mapbtn)
        mapbtn.setOnClickListener {
            val url = "https://map.naver.com/p?c=15.00,0,0,0,dh" // 이동할 웹사이트 URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }


    }
}