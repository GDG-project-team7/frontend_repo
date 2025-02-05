package com.example.hiddenplace

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.guide.estimate_list

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val joinButton: Button = findViewById(R.id.join) // 버튼 ID는 joinButton으로 가정

        joinButton.setOnClickListener {
            // EstimateItemActivity로 이동
            val intent = Intent(this, estimate_list::class.java)
            startActivity(intent)
        }


    }
}