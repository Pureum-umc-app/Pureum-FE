package kr.co.domain.model

data class WaitingBattleResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<WaitingBattle>
)