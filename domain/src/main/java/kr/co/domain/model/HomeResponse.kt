package kr.co.domain.model

data class HomeResponse(
    var goalTime: Int,
    var prevUsageTime: List<UsageTimeDto>,
    var prevRank: List<RankDto>,
)
