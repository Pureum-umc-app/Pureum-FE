package kr.co.pureum.views.battle

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.AllBattleProgMoreDto
import kr.co.domain.model.BattleInfo
import kr.co.domain.model.BattleLike
import kr.co.domain.model.BattleLikeDto
import kr.co.domain.model.BlameBattleSentenceResponse
import kr.co.domain.model.BlameSentenceResponse
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class AllBattleProgInfoViewModel  @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _allBattleProgInfoListLiveData = MutableLiveData<AllBattleProgMoreDto>()
    private val _battleLikeResponseLiveData = MutableLiveData<BattleLikeDto>()
    private var _blameBattleSentenceLiveData = MutableLiveData<BlameBattleSentenceResponse>()

    val allBattleProgressListLiveData: LiveData<AllBattleProgMoreDto>
        get() = _allBattleProgInfoListLiveData

    val blameBattleSentenceLiveData : LiveData<BlameBattleSentenceResponse>
        get() = _blameBattleSentenceLiveData

    fun getAllBattleProgMoreInfo(itemId: Long) {
        viewModelScope.launch {
            val res = repository.getAllBattleProgMoreInfo(itemId)
            Log.e(ContentValues.TAG, "getAllBattleProgMoreInfo : $res")
            _allBattleProgInfoListLiveData.value = res.result
        }
    }

    fun postBattleLike(sentenceId: Long, itemId: Long) {
        viewModelScope.launch {
            val res = repository.postBattleLike(sentenceId, PureumApplication.spfManager.getUserId())
            _battleLikeResponseLiveData.value = res.result
            getAllBattleProgMoreInfo(itemId)
        }
    }

    fun blameBattleSentence(battleSentenceId : Long) {
        viewModelScope.launch {
            val res = repository.blameBattleSentence(battleSentenceId)
            _blameBattleSentenceLiveData.value = res
        }
    }


}