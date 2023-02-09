package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.AllBattleCompMoreDto
import kr.co.domain.model.MyBattleCompMoreDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class AllBattleCompInfoViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _allBattleCompInfoListLiveData = MutableLiveData<AllBattleCompMoreDto>()
    val allBattleCompListLiveData: LiveData<AllBattleCompMoreDto>
        get() = _allBattleCompInfoListLiveData

    fun getAllBattleCompMoreInfo() {
        viewModelScope.launch {
            val res = repository.getAllBattleCompMoreInfo()
            _allBattleCompInfoListLiveData.value = res.result
        }
    }

}