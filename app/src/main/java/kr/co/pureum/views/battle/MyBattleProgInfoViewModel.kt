package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class MyBattleProgInfoViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleProgInfoListLiveData = MutableLiveData<MyBattleProgMoreDto>()
    val myBattleProgressListLiveData: LiveData<MyBattleProgMoreDto>
        get() = _myBattleProgInfoListLiveData

    fun getMyBattleProgressInfo() {
        viewModelScope.launch {
            val res = repository.getMyBattleProgMoreInfo()
            _myBattleProgInfoListLiveData.value = res
        }
    }

}