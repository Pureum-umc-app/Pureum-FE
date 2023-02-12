package kr.co.domain.model

data class WriteSentencesResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: WriteSentencesDto
)
