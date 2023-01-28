package kr.co.domain.model

data class DataWrittenSentence(
    val userProfile: Int,
    val userNickname: String,
    val uploadTime: String,
    val likeNumber: String,
    val writtenSentence: String
)
