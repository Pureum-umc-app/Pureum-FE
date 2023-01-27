package ko.co.data.source.ranking

import kr.co.domain.model.UserRankDto
import kr.co.domain.repository.RankingRepository
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
    private val dataSource: RankingDataSource
) : RankingRepository{
    override suspend fun getMyRank(): UserRankDto =
        dataSource.getMyRank()

    override suspend fun getRankInfo() : List<UserRankDto> =
        dataSource.getRankInfo()

    override suspend fun getMoreRankInfo() : List<UserRankDto> =
        dataSource.getMoreRankInfo()
}