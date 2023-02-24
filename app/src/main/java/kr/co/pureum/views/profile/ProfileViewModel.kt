package kr.co.pureum.views.profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.*
import kr.co.domain.repository.ProfileRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _profileInfoLiveData = MutableLiveData<ProfileInfo>()
    private val _withdrawalResponseLiveData = MutableLiveData<String>()
    private val _nicknameValidationLiveData = MutableLiveData<String>()
    private val _editProfileResponseLiveData = MutableLiveData<String>()
    private val _countLiveData = MutableLiveData<Int>()
    private val _countOpenLiveData = MutableLiveData<Int>()
    private val _mySentenceListLiveData = MutableLiveData<List<MySentenceList>>()
    private val _mySentenceResponseLiveData = MutableLiveData<DefaultResponse>()

    val profileInfoLiveData: LiveData<ProfileInfo> = _profileInfoLiveData
    val withdrawalResponseLiveData: LiveData<String> = _withdrawalResponseLiveData
    val nicknameValidationLiveData: LiveData<String> = _nicknameValidationLiveData
    val editProfileResponseLiveData: LiveData<String> = _editProfileResponseLiveData
    val countLiveData: LiveData<Int> = _countLiveData
    val countOpenLiveData: LiveData<Int> = _countOpenLiveData
    val mySentenceListLiveData: LiveData<List<MySentenceList>> = _mySentenceListLiveData
    val mySentenceResponseLiveData: LiveData<DefaultResponse> = _mySentenceResponseLiveData

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

    fun nicknameValidation(nickname: String) {
        viewModelScope.launch {
            val res = repository.nicknameValidate(nickname)
            _nicknameValidationLiveData.value = res.result
        }
    }

    fun editProfile(context: Context, imageUri: Uri?, nickname: String) {
        viewModelScope.launch {
            val res = repository.editProfile(PureumApplication.spfManager.getUserId(), context, imageUri, nickname)
            _editProfileResponseLiveData.value = res.result
        }
    }
    fun getMySentencesList() {
        viewModelScope.launch {
            val res = repository.getMySentenceList().result
            _countLiveData.value = res.count
            _countOpenLiveData.value = res.countOpen
            _mySentenceListLiveData.value = res.mySentence
        }
    }
    fun deleteMySentence(sentenceId: Long) {
        viewModelScope.launch {
            val res = repository.deleteMySentence(sentenceId)
            _mySentenceResponseLiveData.value = res
        }
    }
}