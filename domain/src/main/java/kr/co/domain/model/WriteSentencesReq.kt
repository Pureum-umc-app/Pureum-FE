package kr.co.domain.model

data class WriteSentencesReq (
    var keywordId: Long,
    var sentence: String,
    var status: String,
    var userId: Long
)