package kr.co.pureum.views.quest

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.SentencesDto
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

    private var _keywordMeaningLiveData = MutableLiveData<String>()
    val keywordMeaningLiveData: LiveData<String>
        get() = _keywordMeaningLiveData

    private var _todaySentenceDate = MutableLiveData<String>()
    val todaySentenceDate: LiveData<String>
        get() = _todaySentenceDate

    private var _todaySentenceListLiveData = MutableLiveData<SentencesDto>()
    val todaySentenceListLiveData
        get() = _todaySentenceListLiveData

    fun getSentencesIncomplete() {
        viewModelScope.launch {
            val res = repository.sentencesIncomplete(PureumApplication.spfManager.getUserId()).result
            _todayKeywordListLiveData.value = res.map { it.keyword }
            _todayKeywordMeaningListLiveData.value = res.map { it.meaning }
        }
    }

    fun getSentencesComplete() {
        viewModelScope.launch {
            val res = repository.sentencesComplete(PureumApplication.spfManager.getUserId())
            //_todayKeywordLiveData.value = res.keyword.toString()
            //_todayKeywordMeaningLiveData.value = res.meaning
            //_todaySentenceDate.value = res.date
        }
    }

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = keyword
    }
}