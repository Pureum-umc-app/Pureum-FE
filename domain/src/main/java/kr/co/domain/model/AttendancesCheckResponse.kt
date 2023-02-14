package kr.co.domain.model

data class AttendancesCheckResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: CheckId
)
