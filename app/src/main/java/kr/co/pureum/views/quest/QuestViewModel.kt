package kr.co.pureum.views.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.BlameSentenceResponse
import kr.co.domain.model.ProfileInfo
import kr.co.domain.model.SentenceLikeResponse
import kr.co.domain.model.SentencesListDto
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

    private var _todayKeywordIdListLiveData = MutableLiveData<List<Long>>()
    val todayKeywordIdListLiveData: LiveData<List<Long>>
        get() = _todayKeywordIdListLiveData

    private var _todayKeywordMeaningListLiveData = MutableLiveData<List<String>>()
    val todayKeywordMeaningListLiveData: MutableLiveData<List<String>>
        get() = _todayKeywordMeaningListLiveData

    private var _incompleteKeywordListLiveData = MutableLiveData<List<String>>()
    val incompleteKeywordListLiveData: LiveData<List<String>>
        get() = _incompleteKeywordListLiveData

    private var _incompleteKeywordMeaningListLiveData = MutableLiveData<List<String>>()
    val incompleteKeywordMeaningListLiveData: LiveData<List<String>>
        get() = _incompleteKeywordMeaningListLiveData

    private var _incompleteKeywordIdListLiveData = MutableLiveData<List<Long>>()
    val incompleteKeywordIdListLiveData: LiveData<List<Long>>
        get() = _incompleteKeywordIdListLiveData

    private var _completeKeywordListLiveData = MutableLiveData<List<String>>()
    val completeKeywordListLiveData: LiveData<List<String>>
        get() = _completeKeywordListLiveData

    private var _completeKeywordMeaningListLiveData = MutableLiveData<List<String>>()
    val completeKeywordMeaningListLiveData: LiveData<List<String>>
        get() = _completeKeywordMeaningListLiveData

    private var _completeKeywordIdListLiveData = MutableLiveData<List<Long>>()
    val completeKeywordIdListLiveData: LiveData<List<Long>>
        get() = _completeKeywordIdListLiveData

    private var _completeKeywordLiveData = MutableLiveData<String>()
    val completeKeywordLiveData: LiveData<String>
        get() = _completeKeywordLiveData

    private var _keywordLiveData = MutableLiveData<String>()
    val keywordLiveData: LiveData<String>
        get() = _keywordLiveData

    private var _keywordIdLiveData = MutableLiveData<Long>()
    val keywordIdLiveData: LiveData<Long>
        get() = _keywordIdLiveData

    private var _todayWriteSentencesResponseLiveData = MutableLiveData<WriteSentencesDto>()
    val todayWriteSentencesResponseLiveData: LiveData<WriteSentencesDto>
        get() = _todayWriteSentencesResponseLiveData

    private var _sentenceListLiveData = MutableLiveData<List<SentencesListDto>?>()
    val sentenceListLiveData: LiveData<List<SentencesListDto>?>
        get() = _sentenceListLiveData

    private var _blameSentenceLiveData = MutableLiveData<BlameSentenceResponse>()
    val blameSentenceLiveData : LiveData<BlameSentenceResponse>
        get()=_blameSentenceLiveData

    private val _sentenceLikeLiveData = MutableLiveData<SentenceLikeResponse>()
    val sentenceLikeLiveData: LiveData<SentenceLikeResponse>
        get() = _sentenceLikeLiveData

    fun getTodayKeyword() {
        viewModelScope.launch {
            val res = repository.todayKeyword(PureumApplication.spfManager.getUserId()).result
            _todayKeywordListLiveData.value = res.map { it.keyword }
            _todayKeywordIdListLiveData.value = res.map { it.keywordId }
            _todayKeywordMeaningListLiveData.value = res.map { it.meaning }
        }
    }


    fun getSentencesIncomplete() {
        viewModelScope.launch {
            val res =
                repository.sentencesIncomplete(PureumApplication.spfManager.getUserId()).result
            _incompleteKeywordListLiveData.value = res.map { it.keyword }
            _incompleteKeywordMeaningListLiveData.value = res.map { it.meaning }
            _incompleteKeywordIdListLiveData.value = res.map { it.keywordId }
        }
    }

    fun getSentencesComplete() {
        viewModelScope.launch {
            val res =
                repository.sentencesComplete(PureumApplication.spfManager.getUserId()).result
            _completeKeywordListLiveData.value = res.map { it.keyword }
            _completeKeywordMeaningListLiveData.value = res.map { it.meaning }
            _completeKeywordIdListLiveData.value = res.map { it.keywordId }
        }
    }

    fun setWordId(wordId: Long) {
        _keywordIdLiveData.value = wordId
    }

    fun setKeyword(keyword: String) {
        _keywordLiveData.value = keyword
    }

    fun setCompleteKeyword(keyword: String) {
        _completeKeywordLiveData.value = keyword
    }

    fun sentencesList(word_id: Long, page: Int, limit: Int, sort: String) {
        viewModelScope.launch {
            val res = repository.sentencesList(
                PureumApplication.spfManager.getUserId(),
                word_id,
                page,
                limit,
                sort
            ).result
            _sentenceListLiveData.value = res
        }
    }

    fun getSentenceWrite(keywordId: Long, sentence: String, status: String) {
        viewModelScope.launch {
            val res = repository.writeSentences(
                keywordId,
                sentence,
                status,
                PureumApplication.spfManager.getUserId()
            )
            _todayWriteSentencesResponseLiveData.value = res.result
        }
    }

    private val _profileInfoLiveData = MutableLiveData<ProfileInfo>()
    val profileInfoLiveData: LiveData<ProfileInfo> = _profileInfoLiveData
    fun getProfileInfo() {
        viewModelScope.launch {
            val res = repository.getProfileInfo(PureumApplication.spfManager.getUserId())
            _profileInfoLiveData.value = res.result
        }
    }

    fun blameSentence(sentenceId : Long) {
        viewModelScope.launch {
            val res = repository.blameSentence(sentenceId)
            _blameSentenceLiveData.value = res
        }
    }

    fun postLike(sentenceId: Long, word_id: Long, page: Int, limit: Int, sort: String) {
        viewModelScope.launch {
            val res = repository.sentenceLike(sentenceId, PureumApplication.spfManager.getUserId())
            _sentenceLikeLiveData.value = res
            sentencesList(word_id,
                page,
                limit,
                sort)
        }
    }
}
