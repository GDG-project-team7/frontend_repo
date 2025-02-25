package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class EstimateCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estimate_check)

        /*
        // 수락 버튼
        val acceptanceButton = findViewById<Button>(R.id.acceptance_button)
        acceptanceButton.setOnClickListener {
            // SendPortfolioActivity로 이동
            val intent = Intent(this, SendPortfolioActivity::class.java)
            startActivity(intent)
        }

        // 거절 버튼
        val rejectionButton = findViewById<Button>(R.id.rejection_button)
        rejectionButton.setOnClickListener {
            // EstimateRejectionActivity로 이동
            val intent = Intent(this, EstimateRejectionActivity::class.java)
            startActivity(intent)
        }
        */

    }
}