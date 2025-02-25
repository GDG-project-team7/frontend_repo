package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R

class GuideMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_main)

        val newEstimateButton: Button = findViewById(R.id.newEstimateButton)
        newEstimateButton.setOnClickListener{
            val intent = Intent(this,EstimateListActivity::class.java)
            startActivity(intent)
        }
    }
}