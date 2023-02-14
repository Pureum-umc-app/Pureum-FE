package kr.co.domain.model

data class WaitingBattle(
    val battleId: Int,
    val duration: Int,
    val otherId: Int,
    val otherNickname: String,
    val otherProfileImg: String,
    val status: String,
    val word: String,
    val wordId: Int
)