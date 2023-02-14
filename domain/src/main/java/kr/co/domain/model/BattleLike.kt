package kr.co.domain.model

data class BattleLike(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: BattleLikeDto
)