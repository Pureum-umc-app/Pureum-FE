package kr.co.domain.model

data class RankDto(
    var year: Int,
    var month: Int,
    var day: Int,
    var rank: List<UserRankDto>
)
