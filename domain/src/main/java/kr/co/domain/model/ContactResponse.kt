package kr.co.domain.model

data class ContactResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)
