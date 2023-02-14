package kr.co.domain.model

data class TodayKeywordResponse (
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<SentencesDto>
)