package kr.co.domain.model

data class AllBattleCompletionDto(
    val battleId: Int,
    val otherProfileImg: String,
    val type: Int,
    val winnerId: Int,
    val winnerNickname: String,
    val winnerProfileImg: String,
    val word: String,
    val wordId: Int
)