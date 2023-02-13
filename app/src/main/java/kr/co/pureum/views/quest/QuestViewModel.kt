package kr.co.pureum.views.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.SentencesDto
import kr.co.domain.model.WriteSentencesDto
import kr.co.domain.repository.QuestRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val repository: QuestRepository
    ) : ViewModel() {
    private var _todayKeywordListLiveData = MutableLiveData<List<String>>()
    val todayKeywordListLiveData: LiveData<List<String>>
        get() = _todayKeywordListLiveData

    private var _keywordLiveData = MutableLiveData<String>()
    val keywordLiveData: LiveData<String>
        get() = _keywordLiveData

    private var _todayKeywordMeaningListLiveData = MutableLiveData<List<String>>()
    val todayKeywordMeaningListLiveData: LiveData<List<String>>
        get() = _todayKeywordMeaningListLiveData

    private var _todayKeywordIdListLiveData = MutableLiveData<List<Long>>()
    val todayKeywordIdListLiveData: LiveData<List<Long>>
        get() = _todayKeywordIdListLiveData

    private var _todayWriteSentencesResponseLiveData = MutableLiveData<WriteSentencesDto>()
    val todayWriteSentencesResponseLiveData: LiveData<WriteSentencesDto>
        get() = _todayWriteSentencesResponseLiveData

    //삭제 할 말... @ @ @ @ @ @ @
    private var _keywordMeaningLiveData = MutableLiveData<String>()
    val keywordMeaningLiveData: LiveData<String>
        get() = _keywordMeaningLiveData

    private var _todaySentenceDate = MutableLiveData<String>()
    val todaySentenceDate: LiveData<String>
        get() = _todaySentenceDate

    private var _todaySentenceListLiveData = MutableLiveData<SentencesDto>()
    val todaySentenceListLiveData
        get() = _todaySentenceListLiveData

    private var _todayWriteSentenceLiveData = MutableLiveData<String>()
    val todayWriteSentenceLiveData: LiveData<String>
        get() = _todayWriteSentenceLiveData

    private var _todayWriteSentenceStatusLiveData = MutableLiveData<String>()
    val todayWriteSentenceStatusLiveData: LiveData<String>
    get() = _todayWriteSentenceStatusLiveData

    fun getSentencesIncomplete() {
        viewModelScope.launch {
            val res = repository.sentencesIncomplete(PureumApplication.spfManager.getUserId()).result
            _todayKeywordListLiveData.value = res.map { it.keyword }
            _todayKeywordMeaningListLiveData.value = res.map { it.meaning }
            _todayKeywordIdListLiveData.value = res.map { it.keywordId }
        }
    }

    fun getSentencesComplete() {
        viewModelScope.launch {
            val res = repository.sentencesComplete(PureumApplication.spfManager.getUserId()).result
            _todayKeywordListLiveData.value = res.map { it.keyword }
        }
    }

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = keyword
    }

    fun getSentenceWrite(keywordId: Long, sentence: String, status: String) {
        viewModelScope.launch {
            val res = repository.writeSentences(keywordId, sentence, status, PureumApplication.spfManager.getUserId())
            _todayWriteSentencesResponseLiveData.value = res.result
        }
    }
}