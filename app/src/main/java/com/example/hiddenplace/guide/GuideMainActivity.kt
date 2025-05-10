package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R

class GuideMainActivity : AppCompatActivity() {
    private var guideId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_main)

        guideId = intent.getIntExtra("userId", 0)

        val newEstimateButton: Button = findViewById(R.id.newEstimateButton)
        newEstimateButton.setOnClickListener{
            val intent = Intent(this,EstimateListActivity::class.java).apply {
                putExtra("GUIDE_ID", guideId)
            }
            startActivity(intent)
        }

        val GuidePortCheckBtn: Button = findViewById(R.id.GuidePortCheckBtn)
        GuidePortCheckBtn.setOnClickListener{
            val intent = Intent(this,CheckPortfolioActivity::class.java)
            startActivity(intent)
        }


    }
}