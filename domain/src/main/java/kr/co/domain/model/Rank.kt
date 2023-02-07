package kr.co.domain.model

data class Rank(
    val image: String,
    val nickname: String,
    val rankNum: Int,
    val useTime: TimeInfo,
    val purposeTime: TimeInfo,
)