package kr.co.pureum.views.home

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.*
import kr.co.domain.repository.HomeRepository
import okhttp3.internal.notify
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {
    private val _homeResponseLiveData = MutableLiveData<HomeResponse>()
    private val _usageTimeLiveData = MutableLiveData<MutableList<UsageTimeDto>>()
    private val _prevRankLiveData = MutableLiveData<List<UserRankDto>>()
    private val _updatedGoalTimeLiveData = MutableLiveData<Int>()

    val homeResponseLiveData: LiveData<HomeResponse> = _homeResponseLiveData
    val usageTimeLiveData: LiveData<MutableList<UsageTimeDto>> = _usageTimeLiveData
    val prevRankLiveData: LiveData<List<UserRankDto>> = _prevRankLiveData
    val updatedGoalTimeLiveData: LiveData<Int> = _updatedGoalTimeLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHomeInfo(usageTime: Int, screenCount: Int) {
        val today = LocalDate.now()
        val todayInfo = UsageTimeDto(
            year = today.year,
            month = today.monthValue,
            day = today.dayOfMonth,
            usageTime = usageTime,
            screenCount = screenCount,
            goalTime = 0,
            isSuccess = false
        )
        viewModelScope.launch {
            val res = repository.getHomeInfo()
            if(res.goalTime != -1) todayInfo.goalTime = res.goalTime
            _usageTimeLiveData.value = (res.prevUsageTime as MutableList<UsageTimeDto>).apply { add(todayInfo) }
            _homeResponseLiveData.value = res
        }
    }

    fun changeDate(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            for (rankData in _homeResponseLiveData.value!!.prevRank) {
                if (rankData.year == year && rankData.month == month && rankData.day == day) {
                    _prevRankLiveData.value = rankData.rank
                    break
                }
            }
        }
    }

    fun updateGoalTime(goalTime: Int) {
        // TODO: 서버로 전송
        viewModelScope.launch {
            repository.updateGoalTime(goalTime)
            _usageTimeLiveData.value!![_usageTimeLiveData.value!!.size - 1].goalTime = goalTime
            _homeResponseLiveData.value!!.goalTime = goalTime
            _updatedGoalTimeLiveData.value = goalTime
            Log.e(TAG, "휴대폰 사용 목표 시간 $goalTime 시간으로 설정")
        }
    }
}
