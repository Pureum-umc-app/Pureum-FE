package kr.co.domain.model

data class MyBattleProgressDto(
    val battleId: Long,
    val challengedId: Int,
    val challengedLikeCnt: Int,
    val challengedNickname: String,
    val challengedProfileImg: String,
    val challengerId: Int,
    val challengerLikeCnt: Int,
    val challengerNickname: String,
    val challengerProfileImg: String,
    val duration: String,
    val isChallengedLike: Int,
    val isChallengerLike: Int,
    val keyword: String,
    val keywordId: Int
)