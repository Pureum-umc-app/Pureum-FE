package kr.co.domain.model

data class WriteSentencesReq (
    var keywordId: Int,
    var sentences: String,
    var status: String,
    var userId: Long
)