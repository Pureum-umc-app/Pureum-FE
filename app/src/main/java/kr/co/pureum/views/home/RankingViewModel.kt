package kr.co.pureum.views.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.domain.model.UserRankDto
import kr.co.domain.repository.RankingRepository
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val repository: RankingRepository
) : ViewModel() {
    companion object {
        const val INIT = 0
        const val MINUS = 1
        const val PLUS = 2
    }

    private var _myRankLiveData = MutableLiveData<UserRankDto>()
    private var _prevRankList : MutableList<UserRankDto> = mutableListOf()
    private var _prevRankListLiveData = MutableLiveData<List<UserRankDto>>()
    val prevRankListLiveData: LiveData<List<UserRankDto>> = _prevRankListLiveData
    val myRankLiveDate : LiveData<UserRankDto> = _myRankLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    var localDate : LocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun isToday() : Boolean = localDate.isEqual(LocalDate.now())

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(option: Int) {
        localDate = when(option) {
            INIT -> LocalDate.now()
            MINUS -> localDate.minusDays(1)
            else -> localDate.plusDays(1)
        }
    }

    fun getMyRank() {
        viewModelScope.launch {
            val res = repository.getMyRank()

            _myRankLiveData.value = res
        }
    }

    fun getRankInfo() {
        viewModelScope.launch {
            val res = repository.getRankInfo()

            _prevRankList.clear()
            _prevRankList.addAll(res)
            _prevRankListLiveData.value = _prevRankList
        }
    }

    fun getAdditionalRankInfo() {
        viewModelScope.launch {
            val res = repository.getMoreRankInfo()

            _prevRankList.addAll(res)
            _prevRankListLiveData.value = _prevRankList
        }
    }
}