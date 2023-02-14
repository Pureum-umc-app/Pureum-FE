package kr.co.domain.model

data class WaitingBattle(
    val battleId: Long,
    val duration: Int,
    val otherId: Long,
    val otherNickname: String,
    val otherProfileImg: String,
    val status: String,
    val word: String,
    val wordId: Long
)