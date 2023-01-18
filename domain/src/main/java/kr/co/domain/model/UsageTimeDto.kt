package kr.co.domain.model

data class UsageTimeDto(
    var year: Int,
    var month: Int,
    var day: Int,
    var usageTime: Int,
    var screenCount: Int,
    var goalTime: Int,
    var isSuccess: Boolean,
)
