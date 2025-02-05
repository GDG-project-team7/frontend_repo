package com.example.hiddenplace.guide

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.R
import org.json.JSONArray

class estimate_list : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EstimateListRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimate_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // JSON 데이터 읽기
        val json = loadJsonFromAssets("forms.json") // assets 폴더의 JSON 파일
        val estimateItems = parseJsonToEstimateItems(json)

        // RecyclerView 어댑터 설정
        adapter = EstimateListRVAdapter(estimateItems) { estimateItem ->
            // JSON 데이터를 기반으로 액티비티 이동
            val intent = Intent(this, EstimateCheckActivity::class.java)
            intent.putExtra("userName", estimateItem.userName)
            intent.putExtra("region", estimateItem.region)
            intent.putExtra("age", estimateItem.age) // 나이 전달
            intent.putExtra("gender", estimateItem.gender) // 성별 전달
            intent.putExtra("text", estimateItem.text) // 요청사항 전달
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    // JSON 파일 읽기 함수
    private fun loadJsonFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    // JSON 데이터를 파싱하여 EstimateItem 리스트로 변환
    private fun parseJsonToEstimateItems(json: String): List<EstimateItem> {
        val jsonArray = JSONArray(json)
        val estimateItems = mutableListOf<EstimateItem>()

        for (i in 0 until jsonArray.length()) {
            val form = jsonArray.getJSONObject(i)
            val user = form.getJSONObject("user")

            val estimateItem = EstimateItem(
                userName = user.getString("userName"),
                region = user.getString("region"),
                age = form.getInt("age"),
                gender = form.getBoolean("gender"),
                text = form.optString("text", "No text provided"), // "text" 기본값 설정
                formId = form.getInt("formId") // "formId" 추가
            )
            estimateItems.add(estimateItem)
        }
        return estimateItems
    }
}
