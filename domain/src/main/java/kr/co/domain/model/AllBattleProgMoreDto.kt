package kr.co.domain.model

data class AllBattleProgMoreDto(
    val battleId: Int,
    val challengedId: Int,
    val challengedImage: String,
    val challengedLikeCnt: Int,
    val challengedNickname: String,
    val challengedSentence: String,
    val challengedSentenceId: Int,
    val challengerId: Int,
    val challengerImage: String,
    val challengerLikeCnt: Int,
    val challengerNickname: String,
    val challengerSentence: String,
    val challengerSentenceId: Int,
    val duration: Int,
    val keyword: String,
    val keywordId: Int,
    val oppLike: Int,
    val remainDuration: String,
    val selfLike: Int,
    val status: String
)