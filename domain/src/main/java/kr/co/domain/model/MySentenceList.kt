package kr.co.domain.model

data class MySentenceList(
    var sentenceId: Long,
    var word: String,
    var sentence: String,
    var countLike: Int,
    var status: String
)
