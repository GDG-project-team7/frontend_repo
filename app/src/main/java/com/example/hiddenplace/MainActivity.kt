package com.example.hiddenplace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.guide.estimate_list

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

// Join 버튼 클릭 이벤트
        val btnJoin: Button = findViewById(R.id.join)
        btnJoin.setOnClickListener {
            // estimate_list 액티비티로 이동
            val intent = Intent(this, estimate_list::class.java)
            startActivity(intent)
        }


    }
}