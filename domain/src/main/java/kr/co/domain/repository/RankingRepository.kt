package kr.co.domain.repository

import kr.co.domain.model.GradeResponse
import kr.co.domain.model.Rank
import kr.co.domain.model.RankResponse

interface RankingRepository {
    suspend fun getMyRank() : Rank
    suspend fun getRankInfo() : List<Rank>
    suspend fun getMoreRankInfo(startPosition: Int) : List<Rank>

    suspend fun getMyGrade(userId: Long) : GradeResponse
    suspend fun getAllRankList(date: String, page: Int): RankResponse
    suspend fun getSameRankList(date: String, page: Int): RankResponse
}