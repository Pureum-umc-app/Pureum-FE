package kr.co.pureum.views.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.LoginDto
import kr.co.domain.repository.LoginRepository
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private val _userTokenLiveData = MutableLiveData<String>()
    private val _nicknameValidationLiveData = MutableLiveData<String>()
    private val _signupResponseLiveData = MutableLiveData<String>()

    val userTokenLiveData: LiveData<String> = _userTokenLiveData
    val nicknameValidationLiveData: LiveData<String> = _nicknameValidationLiveData
    val signupResponseLiveData: LiveData<String> = _signupResponseLiveData

    fun login(kakaoToken: String) {
        viewModelScope.launch {
            val res = repository.login(LoginDto(kakaoToken))
            _userTokenLiveData.value = res.result.jwt
        }
    }

    fun nicknameValidation(nickname: String) {
        viewModelScope.launch {
            val res = repository.nicknameValidate(nickname)
            _nicknameValidationLiveData.value = res.result
        }
    }

    fun signup(imageFile: File?, grade: Int, nickname: String, kakaoToken: String) {
        viewModelScope.launch {
            val res = repository.signup(imageFile, grade, nickname, kakaoToken)
            _signupResponseLiveData.value = res.result
        }
    }
}