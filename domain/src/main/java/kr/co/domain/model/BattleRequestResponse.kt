package kr.co.domain.model

data class BattleRequestResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Long
)