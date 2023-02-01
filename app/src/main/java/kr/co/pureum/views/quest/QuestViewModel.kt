package kr.co.pureum.views.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestViewModel : ViewModel() {
    private var _todayKeywordLiveData = MutableLiveData<String>()
    val todayKeyWordLiveData: LiveData<String>
        get() = _todayKeywordLiveData

    private var _uploadTimeLiveData = MutableLiveData<String>()
    val uploadTimeLiveData: LiveData<String>
        get() = _uploadTimeLiveData
}