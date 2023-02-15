package kr.co.domain.model

data class MyBattleProgMoreDto (
    var battleId: Long,
    var duration: Int,
    var keyword: String,
    var keywordId: Int,
    var myId: Long,
    var myImage: String,
    var myLike: Int,
    var myLikeCnt: Int,
    var myNickname: String,
    var mySentence: String,
    var mySentenceId: Long,
    var oppId: Long,
    var oppImage: String,
    var oppLike: Int,
    var oppLikeCnt: Int,
    var oppNickname: String,
    var oppSentence: String,
    var oppSentenceId: Long,
    var remainDuration: String,
    var status: String
)