package kr.co.domain.model

data class SentencesListResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: List<SentencesListDto>
)
