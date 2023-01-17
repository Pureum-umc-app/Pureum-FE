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

    private var _prevUsageTimeLiveData = MutableLiveData<List<UsageTimeDto>>()
    val prevUsageTimeLiveData: LiveData<List<UsageTimeDto>>
        get() = _prevUsageTimeLiveData

    private var _prevRankListLiveData = MutableLiveData<List<RankDto>>()

    private var _prevRankLiveData = MutableLiveData<List<UserRankDto>>()
    val prevRankLiveData: LiveData<List<UserRankDto>>
        get() = _prevRankLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHomeInfo() {
        viewModelScope.launch {
            // TODO: 임시
            val res = HomeResponse(goalTime = 640,
                prevUsageTime = List(5) {dateIdx ->
                    val tempDate = LocalDate.now().plusDays((dateIdx - 4).toLong())
                    UsageTimeDto(year = tempDate.year, month = tempDate.monthValue, day = tempDate.dayOfMonth,
                        usageTime = 300 + dateIdx * 10, screenCount = dateIdx + 10, goalTime = 300 + dateIdx * 60, isSuccess = true)
                },
                prevRank = List(5) { dateIdx ->
                    val tempDate = LocalDate.now().plusDays((dateIdx - 4).toLong())
                    RankDto(year = tempDate.year, month = tempDate.monthValue, day = tempDate.dayOfMonth,
                        rank = List(5) { userIdx ->
                            UserRankDto(nickname = "%d일의 User %d".format(tempDate.dayOfMonth, userIdx + 1), profileImage = "",
                                usageTime = 300 + userIdx * 10, goalTime = 480)
                        }
                    )
                }
            )
            _goalTimeLiveData.value = res.goalTime
            _prevUsageTimeLiveData.value = res.prevUsageTime
            _prevRankListLiveData.value = res.prevRank
        }
    }

    fun changeDate(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            for (rankData in _prevRankListLiveData.value!!) {
                if (rankData.year == year && rankData.month == month && rankData.day == day) {
                    _prevRankLiveData.value = rankData.rank
                    break
                }
            }
        }
    }
}
