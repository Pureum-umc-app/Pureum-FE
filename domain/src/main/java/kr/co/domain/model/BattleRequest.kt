package kr.co.domain.model

data class BattleRequest(
    val challengedId: Long,
    val challengerId: Long,
    val duration: Int,
    val sentence: String,
    val wordId: Long
)