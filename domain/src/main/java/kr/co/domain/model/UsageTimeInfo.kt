package kr.co.domain.model

import java.time.LocalDateTime

data class UsageTimeInfo(
    var goalTime: Int,
    var usageTime: Int,
    var dateTime: LocalDateTime,
)
