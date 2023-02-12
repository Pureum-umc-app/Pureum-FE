package kr.co.domain.model

data class GradeResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: GradeDto
)