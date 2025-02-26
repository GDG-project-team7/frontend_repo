package com.example.hiddenplace.guest

object NumToRegion {
    private val NumtoRegionMap = mapOf(
        1 to "서울",
        2 to "인천",
        3 to "부산",
        4 to "대구",
        5 to "광주",
        6 to "대전",
        7 to "제주",
        8 to "울산")
    fun getRegionName(regionId: Int): String {
        return NumtoRegionMap[regionId] ?: "알 수 없음" // 만약 ID가 없으면 기본값 반환
    }
}
