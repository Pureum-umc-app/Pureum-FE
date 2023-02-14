package kr.co.domain.model

data class MyBattleCompletionDto(
    val battleId: Long,
    val otherProfileImg: String,
    val situation: Int,
    val winnerId: Int,
    val winnerNickname: String,
    val winnerProfileImg: String,
    val word: String,
    val wordId: Int
)