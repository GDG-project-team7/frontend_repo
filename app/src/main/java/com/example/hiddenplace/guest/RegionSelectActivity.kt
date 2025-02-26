package com.example.hiddenplace.guest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R

class RegionSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_select)

        // 지역별 regionId 매핑
        val regionMap = mapOf(
            R.id.btnSeoul to 1,
            R.id.btnIncheon to 2,
            R.id.btnBusan to 3,
            R.id.btnDaegu to 4,
            R.id.btnGwangju to 5,
            R.id.btnDaejeon to 6,
            R.id.btnJeju to 7,
            R.id.btnUlsan to 8,
        )

        // 각 버튼에 클릭 이벤트 설정
        regionMap.forEach { (buttonId, regionId) ->
            findViewById<Button>(buttonId).setOnClickListener {
                navigateToGuideList(regionId)
            }
        }
    }
    private fun navigateToGuideList(regionId: Int) {
        val intent = Intent(this, SelectGuideActivity::class.java).apply {
            putExtra("regionId", regionId)
        }
        startActivity(intent)
    }
}