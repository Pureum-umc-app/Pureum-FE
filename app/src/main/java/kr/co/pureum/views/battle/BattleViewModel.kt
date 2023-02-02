package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.WaitingBattleDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class BattleViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _waitingBattleListLiveData = MutableLiveData<List<WaitingBattleDto>>()
    val waitingBattleListLiveData: LiveData<List<WaitingBattleDto>> = _waitingBattleListLiveData

    fun getWaitingBattleInfo() {
        viewModelScope.launch {
            val res = repository.getWaitingBattleInfo()
            _waitingBattleListLiveData.value = res
        }
    }
}