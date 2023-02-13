package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.Keyword
import kr.co.domain.model.Opponent
import kr.co.domain.model.ProfileImage
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class OnBattleViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _keywordsLiveData = MutableLiveData<List<Keyword>>()
    private val _keywordLiveData = MutableLiveData<Keyword>()
    private val _sentenceLiveData = MutableLiveData<String>()
    private val _opponentsLiveData = MutableLiveData<List<Opponent>>()
    private val _opponentLiveData = MutableLiveData<Opponent>()
    private val _profileImageLiveData = MutableLiveData<String>()
    private val _requestLiveData = MutableLiveData<Long>()

    val keywordsLiveData: LiveData<List<Keyword>> = _keywordsLiveData
    val keywordLiveData: LiveData<Keyword> = _keywordLiveData
    val sentenceLiveData: LiveData<String> = _sentenceLiveData
    val opponentSLiveDate: LiveData<List<Opponent>> = _opponentsLiveData
    val opponentLiveData: LiveData<Opponent> = _opponentLiveData
    val profileImageLiveData: LiveData<String> = _profileImageLiveData
    val requestLiveData: LiveData<Long> = _requestLiveData

    fun getThreeKeywords() {
        viewModelScope.launch {
            val res = repository.getThreeKeywords(PureumApplication.spfManager.getUserId())
            _keywordsLiveData.value = res.result
        }
    }

    fun getOpponentsList() {
        viewModelScope.launch {
            val res = repository.getOpponentsList(PureumApplication.spfManager.getUserId())
            _opponentsLiveData.value = res.result
        }
    }

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = _keywordsLiveData.value!!.find { it.word == keyword }
    }

    fun setSentence(sentence: String) {
        _sentenceLiveData.value = sentence
    }

    fun setOpponentWithIndex(index: Int) {
        _opponentLiveData.value = _opponentsLiveData.value!![index]
    }

    fun getMyProfileImage() {
        viewModelScope.launch {
            val res = repository.getMyProfileImage(PureumApplication.spfManager.getUserId())
            _profileImageLiveData.value = res.result.image
        }
    }

    fun sendBattleRequest(duration: Int) {
        viewModelScope.launch {
            val res = repository.sendBattleRequest(
                userId = PureumApplication.spfManager.getUserId(),
                opponentId = opponentLiveData.value!!.userId,
                wordId = keywordLiveData.value!!.wordId,
                sentence = sentenceLiveData.value!!,
                duration
            )
            _requestLiveData.value = res.result
        }
    }
}