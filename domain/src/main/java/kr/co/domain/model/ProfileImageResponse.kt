package kr.co.domain.model

data class ProfileImageResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ProfileImage
)