package ko.co.data.source.home

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val pureumService: PureumService
){
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getHomeInfo(userId: Long): HomeResponse {
        var homeResponse = HomeResponse(
            code = 0,
            isSuccess = true,
            message = "",
            result = List(5) { dateIdx ->
                val tempDate = LocalDate.now().minusDays((5 - dateIdx).toLong())
                HomeInfo(
                    count = 0,
                    date = TimeInfo(
                        year = tempDate.year,
                        month = tempDate.monthValue,
                        day = tempDate.dayOfMonth,
                        minutes = 300 + dateIdx * 60,
                    ),
                    purposeTime = TimeInfo(
                        year = tempDate.year,
                        month = tempDate.monthValue,
                        day = tempDate.dayOfMonth,
                        minutes = 300 + dateIdx * 60,
                    ),
                    rank = List(5) {
                        Rank(
                            image = "",
                            nickname = "User %d".format(it + 1),
                            rankNum = it + 1,
                            useTime = TimeInfo(
                                year = tempDate.year,
                                month = tempDate.monthValue,
                                day = tempDate.dayOfMonth,
                                minutes = 300 + it * 10,
                            ),
                            purposeTime = TimeInfo(
                                year = tempDate.year,
                                month = tempDate.monthValue,
                                day = tempDate.dayOfMonth,
                                minutes = 480,
                            ),
                        )
                    },
                    useTime = TimeInfo(
                        year = tempDate.year,
                        month = tempDate.monthValue,
                        day = tempDate.dayOfMonth,
                        minutes = 300 + dateIdx * 10,
                    )
                )
            }
        )
        withContext(Dispatchers.IO) {
//            homeResponse = pureumService.getHomeInfo(userId)
            runCatching {
                pureumService.getHomeInfo(userId)
            }.onSuccess {
                homeResponse = it
            }.onFailure {
                Log.e(TAG, "Home Info Get Failed: $it")
            }
        }
        return homeResponse
    }

    suspend fun setPurposeTime(userId: Long, purposeTime: Int): DefaultResponse {
        var response = DefaultResponse(0, true, "", "")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.setPurposeTime(userId, SetUsageTimeReq((purposeTime / 60).toString(), (purposeTime % 60).toString()))
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "setPurposeTime Failed: $it")
            }
        }
        return response
    }
}