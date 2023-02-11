package kr.co.domain.model

import java.time.Duration

data class AllBattleProgressDto(
    val battleId: Int,
    val challengedId: Int,
    val challengedLikeCnt: Int,
    val challengedNickname: String,
    val challengedProfileImg: Any,
    val challengerId: Int,
    val challengerLikeCnt: Int,
    val challengerNickname: String,
    val challengerProfileImg: String,
    val isChallengedLike: Int,
    val isChallengerLike: Int,
    val keyword: String,
    val keywordId: Int,
    val duration: String
)