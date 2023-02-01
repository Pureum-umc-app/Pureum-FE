package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class MyBattleProgressViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleProgressListLiveData = MutableLiveData<List<MyBattleProgressDto>>()
    val myBattleProgressListLiveData: LiveData<List<MyBattleProgressDto>>
        get() = _myBattleProgressListLiveData

    fun getMyBattleProgressInfo() {
        viewModelScope.launch {
            val res = repository.getMyBattleProgressInfo()
            _myBattleProgressListLiveData.value = res
        }
    }

}