package kr.co.pureum.views.battle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class MyBattleProgressViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleProgressListLiveData = MutableLiveData<List<MyBattleProgressDto>>()
    val myBattleProgressListLiveData: LiveData<List<MyBattleProgressDto>>
        get() = _myBattleProgressListLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMyBattleProgressInfo() {
        viewModelScope.launch {
            val res = repository.getMyBattleProgressInfo(PureumApplication.spfManager.getUserId())
            _myBattleProgressListLiveData.value = res.result
        }
    }

}