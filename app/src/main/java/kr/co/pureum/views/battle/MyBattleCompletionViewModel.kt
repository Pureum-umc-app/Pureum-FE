package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class MyBattleCompletionViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleCompletionListLiveData = MutableLiveData<List<MyBattleCompletionDto>>()
    val myBattleCompletionListLiveData: LiveData<List<MyBattleCompletionDto>>
        get() = _myBattleCompletionListLiveData

    fun getMyBattleCompletionInfo() {
        viewModelScope.launch {
            val res = repository.getMyBattleCompletionInfo()
            _myBattleCompletionListLiveData.value = res
        }
    }

}