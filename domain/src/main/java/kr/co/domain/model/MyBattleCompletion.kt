package kr.co.domain.model

data class MyBattleCompletion(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<MyBattleCompletionDto>
)