package ko.co.data.source.home

import android.os.Build
import androidx.annotation.RequiresApi
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.*
import java.time.LocalDate
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val pureumService: PureumService
){
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getHomeInfo(): HomeResponse {
        val homeResponse = HomeResponse(goalTime = -1,
            prevUsageTime = List(5) { dateIdx ->
                val tempDate = LocalDate.now().minusDays((5 - dateIdx).toLong())
                UsageTimeDto(
                    year = tempDate.year,
                    month = tempDate.monthValue,
                    day = tempDate.dayOfMonth,
                    usageTime = 300 + dateIdx * 10,
                    screenCount = dateIdx + 10,
                    goalTime = 300 + dateIdx * 60,
                    isSuccess = true
                )
            },
            prevRank = List(5) { dateIdx ->
                val tempDate = LocalDate.now().minusDays((5 - dateIdx).toLong())
                RankDto(year = tempDate.year,
                    month = tempDate.monthValue,
                    day = tempDate.dayOfMonth,
                    rank = List(5) { userIdx ->
                        UserRankDto(
                            nickname = "%d일의 User %d".format(tempDate.dayOfMonth, userIdx + 1),
                            profileImage = "",
                            usageTime = 300 + userIdx * 10,
                            goalTime = 480
                        )
                    }
                )
            }
        )
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return homeResponse
    }

    suspend fun updateGoalTime(goalTime: Int): DefaultResponse {
        val defaultResponse = DefaultResponse(0, true, "", "")
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return defaultResponse
    }
}