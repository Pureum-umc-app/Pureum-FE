package kr.co.domain.model

data class SentenceLikeResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SentenceLikeDto
)
