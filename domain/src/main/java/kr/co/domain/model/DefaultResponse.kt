package kr.co.domain.model

data class DefaultResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: String
)