package kr.co.domain.model

data class MyBattleProgMoreDto (
    var keyword: String,
    var nickname: String,
    var mySentence: String,
    var opponentNickname: String,
    var day: Int,
    var opponentSentence: String,
    var mySentenceLikeNum: Int,
    var opSentenceLikeNum: Int,
    var mySentenceLike: Boolean,
    var opSentenceLike: Boolean
    )