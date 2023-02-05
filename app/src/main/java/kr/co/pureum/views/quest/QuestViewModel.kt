package kr.co.pureum.views.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.repository.QuestRepository
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val repository: QuestRepository
    ) : ViewModel() {
    private var _todayKeywordLiveData = MutableLiveData<String>()
    val todayKeywordLiveData: LiveData<String>
        get() = _todayKeywordLiveData

    private var _todayKeywordMeaningLiveData = MutableLiveData<String>()
    val todayKeywordMeaningLiveData: LiveData<String>
        get() = _todayKeywordMeaningLiveData

    private var _todaySentenceDate = MutableLiveData<String>()
    val todaySentenceDate: LiveData<String>
        get() = _todaySentenceDate



    suspend fun getSentencesIncomplete() {
        viewModelScope.launch {
            val res = repository.sentencesIncomplete(1)
            _todayKeywordLiveData.value = res.keyword.toString()
            _todayKeywordMeaningLiveData.value = res.meaning
            _todaySentenceDate.value = res.date
        }
    }

    suspend fun getSentencesComplete() {
        viewModelScope.launch {
            val res = repository.sentencesComplete(1)
            _todayKeywordLiveData.value = res.keyword.toString()
            _todayKeywordMeaningLiveData.value = res.meaning
            _todaySentenceDate.value = res.date
        }
    }
}