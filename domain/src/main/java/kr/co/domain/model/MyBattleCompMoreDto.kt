package kr.co.domain.model

data class MyBattleCompMoreDto(
    val battleId: Int,
    val challengedId: Int,
    val challengedImage: String,
    val challengedLikeCnt: Int,
    val challengedNickname: String,
    val challengedSentence: String,
    val challengedSentenceId: Int,
    val challengerId: Int,
    val challengerImage: Any,
    val challengerLikeCnt: Int,
    val challengerNickname: String,
    val challengerSentence: String,
    val challengerSentenceId: Int,
    val duration: Int,
    val oppLike: Int,
    val userLike: Int,
    val winnerUserId: Int
)