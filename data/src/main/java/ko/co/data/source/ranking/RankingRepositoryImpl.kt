package ko.co.data.source.ranking

import kr.co.domain.model.Rank
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
}