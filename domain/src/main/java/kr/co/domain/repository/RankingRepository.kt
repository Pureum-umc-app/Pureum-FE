package kr.co.domain.repository

import kr.co.domain.model.Rank

interface RankingRepository {
    suspend fun getMyRank() : Rank
    suspend fun getRankInfo() : List<Rank>
    suspend fun getMoreRankInfo(startPosition: Int) : List<Rank>
}