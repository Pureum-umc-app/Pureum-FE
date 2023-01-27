package kr.co.domain.model

data class NicknameValidationResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: String
)