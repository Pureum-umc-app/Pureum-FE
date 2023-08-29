package kr.co.domain.model

data class BlameSentenceResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: String
)
