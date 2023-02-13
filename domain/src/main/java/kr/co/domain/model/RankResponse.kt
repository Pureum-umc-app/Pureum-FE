package kr.co.domain.model

data class RankResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: RankInfo
)