package kr.co.pureum.views.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.repository.BadgeRepository
import javax.inject.Inject

@HiltViewModel
class QuestBadgeViewModel @Inject constructor(
    private val repository: BadgeRepository
) : ViewModel() {
    private val _badgeListLiveData = MutableLiveData<List<Int>>()

    val badgeListLiveData: LiveData<List<Int>> = _badgeListLiveData

    fun getBadgeInfo() {
        viewModelScope.launch {
            val res = repository.getBadgeInfo()
            _badgeListLiveData.value = res
        }
    }
}