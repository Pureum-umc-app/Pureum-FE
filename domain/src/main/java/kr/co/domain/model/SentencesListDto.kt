package kr.co.domain.model

data class SentencesListDto(
    var date: String,
    var isBlamed: String,
    var isLiked: String,
    var keyword: String,
    var keywordId: Long,
    var likeCnt: Int,
    var nickname: String,
    var profileImg: String,
    var sentence: String,
    var sentenceId: Long,
    var userId: Long
) {
    companion object {
        const val BLAME_TRUE: String = "T"
        const val BLAME_FALSE: String = "F"
    }
}
