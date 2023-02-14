package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.WaitingBattle
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class BattleViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _waitingBattleList = mutableListOf<WaitingBattle>()
    private val _waitingBattleListLiveData = MutableLiveData<List<WaitingBattle>>()

    val waitingBattleListLiveData: LiveData<List<WaitingBattle>> = _waitingBattleListLiveData

    fun getWaitingBattleInfo() {
        viewModelScope.launch {
            val res = repository.getWaitingBattleInfo(PureumApplication.spfManager.getUserId(), 5, _waitingBattleList.size / 5)
            _waitingBattleList.addAll(res.result)
            _waitingBattleListLiveData.value =_waitingBattleList
        }
    }
}