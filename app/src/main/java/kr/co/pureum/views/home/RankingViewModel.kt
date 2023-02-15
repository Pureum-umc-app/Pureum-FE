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
import kr.co.domain.model.Rank
import kr.co.domain.model.TimeInfo
import kr.co.domain.model.UserRankDto
import kr.co.domain.repository.RankingRepository
import kr.co.pureum.di.PureumApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

    private val _gradeLiveData = MutableLiveData<String>()
    private val _myRankLiveData = MutableLiveData<Rank>()
    private var _rankList : MutableList<Rank> = mutableListOf()
    private val _rankListLiveData = MutableLiveData<List<Rank>>()

    val myRankLiveDate : LiveData<Rank> = _myRankLiveData
    val gradeLiveData: LiveData<String> = _gradeLiveData
    val rankListLiveData: LiveData<List<Rank>> = _rankListLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    var localDate : LocalDate = LocalDate.now().minusDays(1)

    @RequiresApi(Build.VERSION_CODES.O)
    fun isYesterday() : Boolean = localDate.isEqual(LocalDate.now().minusDays(1))

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(option: Int) {
        localDate = when(option) {
            INIT -> LocalDate.now().minusDays(1)
            MINUS -> localDate.minusDays(1)
            else -> localDate.plusDays(1)
        }
        _rankList.clear()
        if (isSame) getSameRankList() else getAllRankList()
    }

    fun getMyGrade() {
        viewModelScope.launch {
            val res = repository.getMyGrade(PureumApplication.spfManager.getUserId())
            _gradeLiveData.value = when(res.result.grade) {
                0 -> "초등학생"
                1 -> "중학교 1학년"
                2 -> "중학교 2학년"
                3 -> "중학교 3학년"
                4 -> "고등학교 1학년"
                5 -> "고등학교 2학년"
                6 -> "고등학교 3학년, N수생"
                else -> "대학생"
            }
        }
    }

    var isSame = true
    @RequiresApi(Build.VERSION_CODES.O)
    fun switchCategory() {
        isSame = !isSame

        _rankList.clear()
        if(isSame) getSameRankList() else getAllRankList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllRankList() {
        viewModelScope.launch {
            val date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val res = repository.getAllRankList(date, _rankList.size / 25)

            _myRankLiveData.value = res.result.myRank

            _rankList.addAll(res.result.allRank)
            _rankListLiveData.value = _rankList
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSameRankList() {
        viewModelScope.launch {
            val date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val res = repository.getSameRankList(date, _rankList.size / 25)

            _myRankLiveData.value = res.result.myRank

            _rankList.addAll(res.result.allRank)
            _rankListLiveData.value = _rankList
        }
    }
}