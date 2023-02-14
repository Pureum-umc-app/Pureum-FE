package kr.co.domain.model

data class AllBattleProgMoreDto(
    val battleId: Long,
    val challengedId: Int,
    val challengedImage: String,
    val challengedLikeCnt: Int,
    val challengedNickname: String,
    val challengedSentence: String,
    val challengedSentenceId: Long,
    val challengerId: Int,
    val challengerImage: String,
    val challengerLikeCnt: Int,
    val challengerNickname: String,
    val challengerSentence: String,
    val challengerSentenceId: Long,
    val duration: Int,
    val keyword: String,
    val keywordId: Int,
    val challengedLike: Int,
    val remainDuration: String,
    val challengerLike: Int,
    val status: String
)