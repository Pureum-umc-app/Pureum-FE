package kr.co.domain.model

data class MySentencesListResponse(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: GetMySentenceRes
)
