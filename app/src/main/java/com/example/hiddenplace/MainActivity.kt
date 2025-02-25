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
import com.example.hiddenplace.guide.EstimateListActivity
import com.example.hiddenplace.guide.GuideMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val travelbutton: Button = findViewById(R.id.travelbutton)
        travelbutton.setOnClickListener{
            val intent = Intent(this, GuideMainActivity::class.java)
            startActivity(intent)
        }

        val joinLoginbtn = findViewById<Button>(R.id.joinLoginbtn) // 버튼 ID 가져오기

        joinLoginbtn.setOnClickListener {
            // GuideJoinActivity로 이동하는 인텐트 생성
            val intent = Intent(this, GuideJoinActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }

        val mapbtn = findViewById<Button>(R.id.mapbtn)
        mapbtn.setOnClickListener {
            val url = "https://map.naver.com/p?c=15.00,0,0,0,dh" // 이동할 웹사이트 URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }


    }
}