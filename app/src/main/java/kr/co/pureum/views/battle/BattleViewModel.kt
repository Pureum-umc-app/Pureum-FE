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
    val waitingBattleListLiveData: LiveData<List<WaitingBattleDto>>
        get() = _waitingBattleListLiveData

    fun getWaitingBattleInfo() {
        viewModelScope.launch {
            val res = repository.getWaitingBattleInfo()
            _waitingBattleListLiveData.value = res
        }
    }

    private val _keywordsLiveData = MutableLiveData<List<String>>()
    val keywordsLiveData: LiveData<List<String>>
        get() = _keywordsLiveData
    fun getThreeKeywords() {
        viewModelScope.launch {
            val res = repository.getThreeKeywords()
            _keywordsLiveData.value = res
        }
    }

    private val _keywordLiveData = MutableLiveData<String>()
    val keywordLiveData: LiveData<String>
        get() = _keywordLiveData

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = keyword
        // TODO: 서버로 전송?
    }
}