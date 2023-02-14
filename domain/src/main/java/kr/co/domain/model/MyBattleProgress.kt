package kr.co.domain.model

data class MyBattleProgress(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<MyBattleProgressDto>
)