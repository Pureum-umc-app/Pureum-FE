package kr.co.domain.repository

import kr.co.domain.model.UserRankDto

interface RankingRepository {
    suspend fun getMyRank() : UserRankDto
    suspend fun getRankInfo() : List<UserRankDto>
    suspend fun getMoreRankInfo() : List<UserRankDto>
}