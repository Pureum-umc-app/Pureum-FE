package kr.co.domain.model

data class DailyRecordResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: UserId
)