package kr.co.domain.model

data class HomeResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<HomeInfo>
)