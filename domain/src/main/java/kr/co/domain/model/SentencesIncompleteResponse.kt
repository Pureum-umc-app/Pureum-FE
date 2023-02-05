package kr.co.domain.model

data class SentencesIncompleteResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<SentencesDto>
)
