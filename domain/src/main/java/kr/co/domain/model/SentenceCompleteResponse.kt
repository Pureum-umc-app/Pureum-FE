package kr.co.domain.model

data class SentenceCompleteResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<SentencesDto>
)
