package kr.co.domain.model

data class MySentenceInfoResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: MySentenceInfoDto
)
