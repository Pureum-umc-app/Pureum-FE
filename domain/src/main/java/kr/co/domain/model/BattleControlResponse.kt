package kr.co.domain.model

data class BattleControlResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: BattleInfo
)