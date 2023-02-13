package kr.co.pureum.views.battle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.AllBattleProgressDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class AllBattleProgressViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _allBattleProgressListLiveData = MutableLiveData<List<AllBattleProgressDto>>()
    val allBattleProgressListLiveData: LiveData<List<AllBattleProgressDto>>
    get() = _allBattleProgressListLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllBattleProgressInfo() {
        viewModelScope.launch {
            val res = repository.getAllBattleProgressInfo()
            _allBattleProgressListLiveData.value = res.result
        }
    }

}