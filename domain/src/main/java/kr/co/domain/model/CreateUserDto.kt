package kr.co.domain.model

data class CreateUserDto(
    val grade: String,
    val nickname: String,
    val kakaoToken: String,
)