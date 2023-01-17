package kr.co.pureum.views.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.domain.model.HomeResponse
import kr.co.domain.model.RankDto
import kr.co.domain.model.UsageTimeDto
import kr.co.domain.model.UserRankDto
import java.time.LocalDate

class HomeViewModel : ViewModel() {
    private var _goalTimeLiveData = MutableLiveData<Int>()
    val goalTimeLiveData: LiveData<Int>
        get() = _goalTimeLiveData

    private var _prevUsageTimeLiveDate = MutableLiveData<List<UsageTimeDto>>()
    val prevUsageTimeLiveDate: LiveData<List<UsageTimeDto>>
        get() = _prevUsageTimeLiveDate

    private var _prevRankLiveDate = MutableLiveData<List<RankDto>>()
    val prevRankLiveDate: LiveData<List<RankDto>>
        get() = _prevRankLiveDate

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHomeInfo() {
        viewModelScope.launch {
            // TODO: 임시
            val res = HomeResponse(goalTime = 640,
                prevUsageTime = List(5) {dateIdx ->
                    val tempDate = LocalDate.now().plusDays((dateIdx - 4).toLong())
                    UsageTimeDto(year = tempDate.year, month = tempDate.monthValue, day = tempDate.dayOfMonth,
                        usageTime = 300 + dateIdx * 10, screenCount = dateIdx, goalTime = 480, isSuccess = true)
                },
                prevRank = List(5) { dateIdx ->
                    val tempDate = LocalDate.now().plusDays((dateIdx - 4).toLong())
                    RankDto(year = tempDate.year, month = tempDate.monthValue, day = tempDate.dayOfMonth,
                        rank = List(5) { userIdx ->
                            UserRankDto(nickname = "User %d".format(userIdx), profileImage = "",
                                usageTime = 300 + userIdx * 10, goalTime = 480)
                        }
                    )
                }
            )
            _goalTimeLiveData.value = res.goalTime
            _prevUsageTimeLiveDate.value = res.prevUsageTime
            _prevRankLiveDate.value = res.prevRank
        }
    }
}
