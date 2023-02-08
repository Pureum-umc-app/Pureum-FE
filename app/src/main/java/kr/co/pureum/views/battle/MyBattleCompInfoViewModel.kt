package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompMoreDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class MyBattleCompInfoViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleCompInfoListLiveData = MutableLiveData<MyBattleCompMoreDto>()
    val myBattleCompListLiveData: LiveData<MyBattleCompMoreDto>
        get() = _myBattleCompInfoListLiveData

    fun getMyBattleCompInfo() {
        viewModelScope.launch {
            val res = repository.getMyBattleCompMoreInfo()
            _myBattleCompInfoListLiveData.value = res.result
        }
    }

}