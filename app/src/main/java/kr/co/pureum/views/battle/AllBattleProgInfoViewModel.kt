package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.AllBattleProgMoreDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class AllBattleProgInfoViewModel  @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _allBattleProgInfoListLiveData = MutableLiveData<AllBattleProgMoreDto>()
    val allBattleProgressListLiveData: LiveData<AllBattleProgMoreDto>
        get() = _allBattleProgInfoListLiveData

    fun getAllBattleProgressInfo() {
        viewModelScope.launch {
            val res = repository.getAllBattleProgMoreInfo()
            _allBattleProgInfoListLiveData.value = res.result
        }
    }

}