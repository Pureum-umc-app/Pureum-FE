package kr.co.domain.model

data class StampListResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: StampListDto
)
