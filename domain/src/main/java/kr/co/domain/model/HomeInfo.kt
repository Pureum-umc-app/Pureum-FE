package kr.co.domain.model

data class HomeInfo(
    val count: Int,
    val date: String,
    val purposeTime: TimeInfo,
    val rank: List<Rank>,
    val useTime: TimeInfo
)