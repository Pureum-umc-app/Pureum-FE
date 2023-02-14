package kr.co.domain.model

data class OpponentsResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Opponent>
)