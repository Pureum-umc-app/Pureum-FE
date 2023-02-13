package kr.co.domain.model

data class AllBattleCompletionDto(
    val battleId: Int,
    val hasResult: Int,
    val otherProfileImg: String,
    val winnerId: Int,
    val winnerNickname: String,
    val winnerProfileImg: String,
    val word: String,
    val wordId: Int
)