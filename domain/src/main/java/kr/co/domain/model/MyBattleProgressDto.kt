package kr.co.domain.model

data class MyBattleProgressDto(
    var keyword: String,
    var firstUserName: String,
    var firstUserProfile: String,
    var secondUserName: String,
    var secondUserProfile: String,
    var day: String,
    var firstLike: String,
    var firstLikeNum: Int,
    var secondLike: String,
    var secondLikeNum: Int

)