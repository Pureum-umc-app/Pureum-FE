package kr.co.domain.model

data class BattleSentenceResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: BattleSentenceDto
)