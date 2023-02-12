package kr.co.domain.model

data class ProfileInfoResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ProfileInfo
)