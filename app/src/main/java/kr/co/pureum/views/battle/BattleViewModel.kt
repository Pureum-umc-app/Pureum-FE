package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.BattleId
import kr.co.domain.model.BattleInfo
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
    private val _battleControlResponseLiveData = MutableLiveData<BattleInfo>()

    val waitingBattleListLiveData: LiveData<List<WaitingBattle>> = _waitingBattleListLiveData
    val battleControlResponseLiveData: LiveData<BattleInfo> = _battleControlResponseLiveData

    fun getWaitingBattleInfo() {
        viewModelScope.launch {
            val res = repository.getWaitingBattleInfo(PureumApplication.spfManager.getUserId(), 5, 0)
            _waitingBattleList.clear()
            _waitingBattleList.addAll(res.result)
            _waitingBattleListLiveData.value =_waitingBattleList
        }
    }

    fun getMoreWaitingBattleInfo() {
        viewModelScope.launch {
            val res = repository.getWaitingBattleInfo(PureumApplication.spfManager.getUserId(), 5, _waitingBattleList.size / 5)
            _waitingBattleList.addAll(res.result)
            _waitingBattleListLiveData.value =_waitingBattleList
        }
    }

    fun acceptBattle(battleId: Long) {
        viewModelScope.launch {
            val res = repository.acceptBattle(BattleId(battleId))
            _battleControlResponseLiveData.value = res.result
        }
    }

    fun refuseBattle(battleId: Long) {
        viewModelScope.launch {
            val res = repository.refuseBattle(BattleId(battleId))
            _battleControlResponseLiveData.value = res.result
        }
    }

    fun cancelBattle(battleId: Long) {
        viewModelScope.launch {
            val res = repository.cancelBattle(BattleId(battleId))
            _battleControlResponseLiveData.value = res.result
        }
    }
}