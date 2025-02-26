package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class EstimateWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estimate_write)


        val EstimateAcceptBtn : Button = findViewById(R.id.EstimateAcceptBtn)
        EstimateAcceptBtn.setOnClickListener{
            Toast.makeText(this, "견적서가 성공적으로 전송되었습니다",Toast.LENGTH_LONG).show()
            val intent = Intent(this, GuestMainActivity::class.java)
            startActivity(intent)

        }
    }
}