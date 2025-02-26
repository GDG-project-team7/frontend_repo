package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.MainActivity
import com.example.hiddenplace.R
import com.example.hiddenplace.guide.CheckPortfolioActivity

class GuestMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest_main)

        // 작성한 견적서 보기 버튼 클릭 이벤트
        val lookRequestBtn: Button = findViewById(R.id.lookRequestBtn)
        lookRequestBtn.setOnClickListener {
            val intent = Intent(this, ViewMyEstimateActivity::class.java)
            startActivity(intent)
        }

        //가이드 찾기 버튼 클릭 시 지역 선택 페이지로 이동
        val findGuideBtn = findViewById<Button>(R.id.findGuideBtn) // 버튼 ID 가져오기

        findGuideBtn.setOnClickListener {
            val intent = Intent(this, RegionSelectActivity::class.java)
            startActivity(intent) // 액티비티 시작


        }

        //게스트 메인에서 로그아웃버튼 클릭 하면 메인화면으로 이동하도록
        val GuestLogoutbtn : Button = findViewById(R.id.GuestLogoutbtn)
        GuestLogoutbtn.setOnClickListener {
            Toast.makeText(this,"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}