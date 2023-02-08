package kr.co.domain.model

data class LoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: LoginJwtToken
)