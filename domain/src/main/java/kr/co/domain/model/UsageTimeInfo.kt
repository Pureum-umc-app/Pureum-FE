package kr.co.domain.model

import java.time.LocalDate

data class UsageTimeInfo(
    var goalTime: Int,
    var usageTime: Int,
    var dateTime: LocalDate,
)
