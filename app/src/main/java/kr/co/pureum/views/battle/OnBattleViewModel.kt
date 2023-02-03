package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.OpponentDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

@HiltViewModel
class OnBattleViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _keywordsLiveData = MutableLiveData<List<String>>()
    private val _keywordLiveData = MutableLiveData<String>()
    private val _definitionLiveData = MutableLiveData<String>()
    private val _sentenceLiveData = MutableLiveData<String>()
    private val _opponentsLiveData = MutableLiveData<List<OpponentDto>>()
    private val _opponentsList: MutableList<OpponentDto> = mutableListOf()
    private val _opponentLiveData = MutableLiveData<OpponentDto>()

    val keywordsLiveData: LiveData<List<String>> = _keywordsLiveData
    val keywordLiveData: LiveData<String> = _keywordLiveData
    val definitionLiveData: LiveData<String> = _definitionLiveData
    val sentenceLiveData: LiveData<String> = _sentenceLiveData
    val opponentSLiveDate: LiveData<List<OpponentDto>> = _opponentsLiveData
    val opponentLiveData: LiveData<OpponentDto> = _opponentLiveData

    fun getThreeKeywords() {
        viewModelScope.launch {
            val res = repository.getThreeKeywords()
            _keywordsLiveData.value = res
        }
    }

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = keyword
        // TODO: 서버로 전송?
    }

    fun getDefinition(keyword: String) {
        viewModelScope.launch {
            val res = repository.getDefinition(keyword)
            _definitionLiveData.value = res
        }
    }

    fun setSentence(sentence: String) {
        _sentenceLiveData.value = sentence
        // TODO: 서버로 전송?
    }

    fun getOpponentsList() {
        viewModelScope.launch {
            val res = repository.getOpponentsList()
            _opponentsList.addAll(res)
            _opponentsLiveData.value = _opponentsList
        }
    }

    fun getAdditionalOpponents() {
        viewModelScope.launch {
            val res = repository.getAdditionalOpponents(_opponentsLiveData.value!!.size, 10)
            _opponentsList.addAll(res)
            _opponentsLiveData.value = _opponentsList
        }
    }

    fun setOpponentWithIndex(index: Int) {
        _opponentLiveData.value = _opponentsList[index]
    }
}