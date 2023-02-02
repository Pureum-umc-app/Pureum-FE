package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class OnBattleViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _keywordsLiveData = MutableLiveData<List<String>>()
    val keywordsLiveData: LiveData<List<String>> = _keywordsLiveData

    fun getThreeKeywords() {
        viewModelScope.launch {
            val res = repository.getThreeKeywords()
            _keywordsLiveData.value = res
        }
    }

    private val _keywordLiveData = MutableLiveData<String>()
    val keywordLiveData: LiveData<String> = _keywordLiveData

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = keyword
        // TODO: 서버로 전송?
    }

    private val _definitionLiveData = MutableLiveData<String>()
    val definitionLiveData: LiveData<String> = _definitionLiveData

    fun getDefinition(keyword: String) {
        viewModelScope.launch {
            val res = repository.getDefinition(keyword)
            _definitionLiveData.value = res
        }
    }
}