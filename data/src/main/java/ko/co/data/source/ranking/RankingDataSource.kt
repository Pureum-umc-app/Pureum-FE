package ko.co.data.source.ranking

import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.UserRankDto
import javax.inject.Inject

class RankingDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getMyRank() : UserRankDto {
        val myRank = UserRankDto(nickname = "김태우", profileImage = "",
            usageTime = 380, goalTime = 480)
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return myRank
    }

    suspend fun getRankInfo() : List<UserRankDto> {
        // TODO: 임시
        val rankList = MutableList(25) { userIdx ->
            UserRankDto(nickname = "User", profileImage = "",
                usageTime = 200 + userIdx * 10, goalTime = 480)
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return rankList
    }

    suspend fun getMoreRankInfo() : List<UserRankDto> {
        // TODO: 임시
        val moreRankList = MutableList(25) { userIdx ->
            UserRankDto(nickname = "User", profileImage = "",
                usageTime = 200 + userIdx * 10, goalTime = 480)
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }
        return moreRankList
    }
}