package kr.co.pureum.views.battle

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
import kr.co.domain.repository.BattleRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class AllBattleProgInfoViewModel  @Inject constructor(
    private val repository: BattleRepository
) : ViewModel() {
    private val _allBattleProgInfoListLiveData = MutableLiveData<AllBattleProgMoreDto>()
    private val _battleLikeResponseLiveData = MutableLiveData<BattleLikeDto>()

    val allBattleProgressListLiveData: LiveData<AllBattleProgMoreDto>
        get() = _allBattleProgInfoListLiveData

    fun getAllBattleProgMoreInfo(itemId: Long) {
        viewModelScope.launch {
            val res = repository.getAllBattleProgMoreInfo(itemId)
            _allBattleProgInfoListLiveData.value = res.result
        }
    }

    fun postBattleLike(sentenceId: Long) {
        viewModelScope.launch {
            val res = repository.postBattleLike(sentenceId, PureumApplication.spfManager.getUserId())
            _battleLikeResponseLiveData.value = res.result
        }
    }


}