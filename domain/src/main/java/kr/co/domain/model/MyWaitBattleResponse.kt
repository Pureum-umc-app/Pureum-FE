package kr.co.domain.model

data class MyWaitBattleResponse (
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: MyWaitBattleDto
)