package ko.co.data.source.ranking

import android.content.ContentValues.TAG
import android.util.Log
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.GradeDto
import kr.co.domain.model.GradeResponse
import kr.co.domain.model.Rank
import kr.co.domain.model.RankInfo
import kr.co.domain.model.RankResponse
import kr.co.domain.model.TimeInfo
import kr.co.domain.model.UserRankDto
import javax.inject.Inject

class RankingDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getMyRank() : Rank {
        val myRank = Rank(image = "", nickname = "김태우", rankNum = 2,
            useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 380),
            purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
        )
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return myRank
    }

    suspend fun getRankInfo() : List<Rank> {
        // TODO: 임시
        val rankList = MutableList(25) {
            Rank(image = "", nickname = "User", rankNum = it + 1,
                useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 200 + it * 10),
                purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
            )
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return rankList
    }

    suspend fun getMoreRankInfo(startPosition: Int) : List<Rank> {
        // TODO: 임시
        val moreRankList = MutableList(25) {
            Rank(image = "", nickname = "User", rankNum = startPosition + it + 1,
                useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 200 + it * 10),
                purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
            )
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }
        return moreRankList
    }

    suspend fun getMyGrade(userId: Long) : GradeResponse {
        var response = GradeResponse(0, false, "error", GradeDto(0, 0))
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getMyGrade(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getMyGrade Failed: $it")
            }
        }
        return response
    }

    suspend fun getAllRankList(date: String, page: Int): RankResponse {
        var response = RankResponse(0, false, "error",
            RankInfo(
                allRank = List(25) {
                    Rank(image = "", nickname = "User", rankNum = it + 1,
                        useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 200 + it * 10),
                        purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
                    )
                },
                myRank = Rank(image = "", nickname = "김태우", rankNum = 2,
                    useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 380),
                    purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
                )
            )
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getAllRankList(date, page)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getAllRankList Failed: $it")
            }
        }
        return response
    }

    suspend fun getSameRankList(date: String, page: Int): RankResponse {
        var response = RankResponse(0, false, "error",
            RankInfo(
                allRank = List(25) {
                    Rank(image = "", nickname = "User", rankNum = it + 1,
                        useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 200 + it * 10),
                        purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
                    )
                },
                myRank = Rank(image = "", nickname = "김태우", rankNum = 2,
                    useTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 380),
                    purposeTime = TimeInfo(year = 0, month = 0, day = 0, minutes = 480),
                )
            )
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getSameRankList(date, page)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getAllRankList Failed: $it")
            }
        }
        return response
    }
}