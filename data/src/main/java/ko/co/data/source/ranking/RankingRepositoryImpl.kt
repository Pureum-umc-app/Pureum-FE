package ko.co.data.source.ranking

import kr.co.domain.model.GradeResponse
import kr.co.domain.model.Rank
import kr.co.domain.model.RankResponse
import kr.co.domain.model.UserRankDto
import kr.co.domain.repository.RankingRepository
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
    private val dataSource: RankingDataSource
) : RankingRepository{
    override suspend fun getMyRank(): Rank =
        dataSource.getMyRank()

    override suspend fun getRankInfo() : List<Rank> =
        dataSource.getRankInfo()

    override suspend fun getMoreRankInfo(startPosition: Int) : List<Rank> =
        dataSource.getMoreRankInfo(startPosition)

    override suspend fun getMyGrade(userId: Long): GradeResponse =
        dataSource.getMyGrade(userId)

    override suspend fun getAllRankList(date: String, page: Int): RankResponse =
        dataSource.getAllRankList(date, page)

    override suspend fun getSameRankList(date: String, page: Int): RankResponse =
        dataSource.getSameRankList(date, page)
}