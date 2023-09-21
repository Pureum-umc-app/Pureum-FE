package kr.co.pureum.views.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.BattleId
import kr.co.domain.model.BattleInfo
import kr.co.domain.model.BattleLikeDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyWaitBattleDto
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class MyBattleProgInfoViewModel @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _myBattleProgInfoListLiveData = MutableLiveData<MyBattleProgMoreDto>()
    private val _battleControlResponseLiveData = MutableLiveData<BattleInfo>()
    private val _battleLikeResponseLiveData = MutableLiveData<BattleLikeDto>()

    private val _myWaitBattleInfoLiveData = MutableLiveData<MyWaitBattleDto>()

    val myBattleProgressListLiveData: LiveData<MyBattleProgMoreDto>
        get() = _myBattleProgInfoListLiveData

    val myWaitBattleInfoLiveData: LiveData<MyWaitBattleDto>
        get() = _myWaitBattleInfoLiveData

    fun getMyBattleProgMoreInfo(itemId: Long) {     // 이거 수정
        viewModelScope.launch {
            val res = repository.getMyBattleProgMoreInfo(itemId)
            _myBattleProgInfoListLiveData.value = res.result
        }
    }

    fun getMyWaitBattleInfo(battleId: Long) {
        viewModelScope.launch {
            val res = repository.getMyWaitBattleInfo(battleId)
            _myWaitBattleInfoLiveData.value = res.result
        }
    }

    fun cancelBattle(battleId: Long) {
        viewModelScope.launch {
            val res = repository.cancelBattle(BattleId(battleId))
            _battleControlResponseLiveData.value = res.result
        }
    }

    fun postBattleLike(sentenceId: Long, itemId: Long) {
        viewModelScope.launch {
            val res = repository.postBattleLike(sentenceId, PureumApplication.spfManager.getUserId())
            _battleLikeResponseLiveData.value = res.result
            getMyBattleProgMoreInfo(itemId)
        }
    }

}