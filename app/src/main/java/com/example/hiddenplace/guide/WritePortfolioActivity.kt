package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class WritePortfolioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_portfolio)

        val writePortfolioBtn = findViewById<Button>(R.id.writePortfolioBtn)

        writePortfolioBtn.setOnClickListener {
            val intent = Intent(this, GuideMainActivity::class.java)
            startActivity(intent) // 액티비티 시작
        }
    }
}