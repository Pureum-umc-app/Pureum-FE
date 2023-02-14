package kr.co.domain.model

data class KeywordsResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Keyword>
)