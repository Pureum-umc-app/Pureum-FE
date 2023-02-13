package kr.co.domain.model

data class SentencesListDto(
    var image: String,
    var keyword: String,
    var keywordId: Long,
    var likeNum: Int,
    var nickname: String,
    var selfLike: Boolean,
    var sentence: String,
    var sentenceId: Long,
    var time: String,
    var userId: Long
)
