package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class guide_main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_main)

        val newEstimateButton: Button = findViewById(R.id.newEstimateButton)
        newEstimateButton.setOnClickListener{
            val intent = Intent(this,estimate_list::class.java)
            startActivity(intent)
        }
    }
}