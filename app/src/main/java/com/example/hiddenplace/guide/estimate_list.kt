package com.example.hiddenplace.guide

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

        // 로컬 JSON 파일 읽기
        val json = loadJsonFromAssets("forms.json") // assets 폴더의 JSON 파일
        val estimateItems = parseJsonToEstimateItems(json)

        // RecyclerView에 데이터 설정
        adapter = EstimateListRVAdapter(estimateItems) { estimateItem ->
            onEstimateItemClicked(estimateItem)
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
                formId = form.getInt("formId")
            )
            estimateItems.add(estimateItem)
        }
        return estimateItems
    }

    // 아이템 클릭 이벤트
    private fun onEstimateItemClicked(estimateItem: EstimateItem) {
        println("Clicked: ${estimateItem.userName}, ${estimateItem.region}")
    }
}
