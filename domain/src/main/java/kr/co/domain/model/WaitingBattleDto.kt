package kr.co.domain.model

data class WaitingBattleDto(
    val word: String,
    val period: Int,
    val message: String,
    val opponentProfile: String,
    val opponentNickname: String,
)
