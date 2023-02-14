package kr.co.pureum.views.battle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.AllBattleCompletionDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class AllBattleCompletionViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _allBattleCompletionListLiveData = MutableLiveData<List<AllBattleCompletionDto>>()
    val allBattleCompletionListLiveData: LiveData<List<AllBattleCompletionDto>>
        get() = _allBattleCompletionListLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllBattleCompletionInfo() {
        viewModelScope.launch {
            val res = repository.getAllBattleCompletionInfo()
            _allBattleCompletionListLiveData.value = res.result
        }
    }

}