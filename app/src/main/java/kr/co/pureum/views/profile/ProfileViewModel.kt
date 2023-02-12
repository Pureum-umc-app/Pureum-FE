package kr.co.pureum.views.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ko.co.data.remote.PureumService
import kotlinx.coroutines.launch
import kr.co.domain.model.ProfileInfo
import kr.co.domain.repository.LoginRepository
import kr.co.domain.repository.ProfileRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _profileInfoLiveData = MutableLiveData<ProfileInfo>()
    private val _withdrawalResponseLiveData = MutableLiveData<String>()

    val profileInfoLiveData: LiveData<ProfileInfo> = _profileInfoLiveData
    val withdrawalResponseLiveData: LiveData<String> = _withdrawalResponseLiveData

    fun getProfileInfo() {
        viewModelScope.launch {
            val res = repository.getProfileInfo(PureumApplication.spfManager.getUserId())
            _profileInfoLiveData.value = res.result
        }
    }

    fun withdrawal() {
        viewModelScope.launch {
            val res = repository.withdrawal(PureumApplication.spfManager.getUserId())
            _withdrawalResponseLiveData.value = res.result
        }
    }
}