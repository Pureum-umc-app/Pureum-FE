package kr.co.domain.model

data class MyBattleCompMoreDto(
    val battleId: Int,
    val duration: Int,
    val loserId: Int,
    val loserImage: String,
    val loserLikeCnt: Int,
    val loserNickname: String,
    val loserSentence: String,
    val loserSentenceId: Int,
    val oppLike: Int,
    val situation:Int,
    val userLike: Int,
    val winnerId: Int,
    val winnerImage: Any,
    val winnerLikeCnt: Int,
    val winnerNickname: String,
    val winnerSentence: String,
    val winnerSentenceId: Int,
    val winnerUserId: Int
)