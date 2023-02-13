package kr.co.domain.model

data class SentencesDto(
    var date: String,
    var keyword: String,
    var keywordId: Long,
    var meaning: String,
    var userId: Long
)
