package kr.co.domain.model

data class MyWaitBattleDto(
    val duration: Int,
    val opponentImage: String,
    val opponentName: String,
    val opponentSentence: String,
    val userImage: String,
    val userName: String,
    val word: String,
    val wordMean: String
)
