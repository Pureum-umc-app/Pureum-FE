package kr.co.domain.model

data class AllBattleProgress(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<AllBattleProgressDto>
)