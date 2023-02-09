package kr.co.domain.model

data class AllBattleCompletion(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<AllBattleCompletionDto>
)