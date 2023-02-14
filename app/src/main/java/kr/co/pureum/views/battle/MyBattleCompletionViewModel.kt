package kr.co.pureum.views.battle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class MyBattleCompletionViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleCompletionListLiveData = MutableLiveData<List<MyBattleCompletionDto>>()
    val myBattleCompletionListLiveData: LiveData<List<MyBattleCompletionDto>>
        get() = _myBattleCompletionListLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMyBattleCompletionInfo() {
        viewModelScope.launch {
            val res = repository.getMyBattleCompletionInfo(PureumApplication.spfManager.getUserId())
            _myBattleCompletionListLiveData.value = res.result
        }
    }

}