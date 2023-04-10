package kr.co.domain.model

data class BadgeResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: BadgeInfo
)