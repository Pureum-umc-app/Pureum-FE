package kr.co.pureum.views.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.CheckId
import kr.co.domain.model.StampInfo
import kr.co.domain.model.UserId
import kr.co.domain.repository.AttendancesRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val repository: AttendancesRepository
) : ViewModel() {
    private val _stampsLiveData = MutableLiveData<StampInfo>()
    private val _attendanceLiveData = MutableLiveData<CheckId>()

    val stampsLiveData: LiveData<StampInfo> = _stampsLiveData
    val attendanceLiveData: LiveData<CheckId> = _attendanceLiveData

    fun getStampList() {
        viewModelScope.launch {
            val res = repository.getStampList(PureumApplication.spfManager.getUserId())
            _stampsLiveData.value = res.result
        }
    }

    fun attendanceCheck() {
        viewModelScope.launch {
            val res = repository.attendancesCheck(UserId(PureumApplication.spfManager.getUserId()))
            _attendanceLiveData.value = res.result
        }
    }
}