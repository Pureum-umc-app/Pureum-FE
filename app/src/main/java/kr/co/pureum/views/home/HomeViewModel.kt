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
import kr.co.pureum.di.PureumApplication.Companion.spfManager
import okhttp3.internal.notify
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {
    private val _homeInfoListLiveData = MutableLiveData<MutableList<HomeInfo>>()
    private val _prevRankLiveData = MutableLiveData<List<Rank>>()
    private val _updatedGoalTimeLiveData = MutableLiveData<Int>()

    val homeInfoListLiveData: LiveData<MutableList<HomeInfo>> = _homeInfoListLiveData
    val prevRankLiveData: LiveData<List<Rank>> = _prevRankLiveData
    val updatedGoalTimeLiveData: LiveData<Int> = _updatedGoalTimeLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHomeInfo(usageTime: Int, screenCount: Int) {
        val today = LocalDate.now()
        val todayInfo = HomeInfo(
            count = screenCount,
            date = TimeInfo(
                year = today.year,
                month = today.monthValue,
                day = today.dayOfMonth,
                minutes = spfManager.getPurposeTime(),
            ),
            purposeTime = TimeInfo(
                year = today.year,
                month = today.monthValue,
                day = today.dayOfMonth,
                minutes = spfManager.getPurposeTime(),
            ),
            rank = listOf(),
            useTime = TimeInfo(
                year = today.year,
                month = today.monthValue,
                day = today.dayOfMonth,
                minutes = usageTime,
            ),
        )
        viewModelScope.launch {
            val res = repository.getHomeInfo(spfManager.getUserId())
            _homeInfoListLiveData.value = (res.result as MutableList<HomeInfo>).apply { add(todayInfo) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeDate(date: LocalDate) {
        viewModelScope.launch {
            for (homeInfo in _homeInfoListLiveData.value!!) {
                if (homeInfo.date.year == date.year && homeInfo.date.month == date.monthValue && homeInfo.date.day == date.dayOfMonth) {
                    _prevRankLiveData.value = homeInfo.rank
                    break
                }
            }
        }
    }

    fun updateGoalTime(purposeTime: Int) {
        // TODO: 서버로 전송
        viewModelScope.launch {
            repository.updateGoalTime(purposeTime)
            spfManager.setPurposeTime(purposeTime)
            _homeInfoListLiveData.value!![_homeInfoListLiveData.value!!.size - 1].purposeTime.minutes = purposeTime
            _updatedGoalTimeLiveData.value = purposeTime
            Log.e(TAG, "휴대폰 사용 목표 시간 $purposeTime 시간으로 설정")
        }
    }
}
