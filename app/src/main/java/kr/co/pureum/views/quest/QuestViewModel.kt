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
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val repository: QuestRepository
    ) : ViewModel() {
    private var _todayKeywordLiveData = MutableLiveData<List<String>>()
    val todayKeywordLiveData: LiveData<List<String>>
        get() = _todayKeywordLiveData

    private var _todayKeywordMeaningLiveData = MutableLiveData<String>()
    val todayKeywordMeaningLiveData: LiveData<String>
        get() = _todayKeywordMeaningLiveData

    private var _todaySentenceDate = MutableLiveData<String>()
    val todaySentenceDate: LiveData<String>
        get() = _todaySentenceDate

    private var _todaySentenceListLiveData = MutableLiveData<SentencesDto>()
    val todaySentenceListLiveData
        get() = _todaySentenceListLiveData

    fun getSentencesIncomplete() {
        viewModelScope.launch {
            val res = repository.sentencesIncomplete(userId = 1)
            _todayKeywordLiveData.value = res.result.map { it.keyword }
            Log.e(TAG, res.result.toString())
            //_todayKeywordMeaningLiveData.value = res.meaning
            //_todaySentenceDate.value = res.date
        }
    }

    fun getSentencesComplete() {
        viewModelScope.launch {
            val res = repository.sentencesComplete(1)
            //_todayKeywordLiveData.value = res.keyword.toString()
            //_todayKeywordMeaningLiveData.value = res.meaning
            //_todaySentenceDate.value = res.date
        }
    }
}