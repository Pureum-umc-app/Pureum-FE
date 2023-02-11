package kr.co.pureum.views.signup

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ko.co.data.remote.PureumLoginService
import kotlinx.coroutines.launch
import kr.co.domain.model.LoginDto
import kr.co.domain.model.UserInfo
import kr.co.domain.repository.LoginRepository
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    private val _nicknameValidationLiveData = MutableLiveData<String>()
    private val _signupResponseLiveData = MutableLiveData<String>()

    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData
    val nicknameValidationLiveData: LiveData<String> = _nicknameValidationLiveData
    val signupResponseLiveData: LiveData<String> = _signupResponseLiveData

    fun login(kakaoToken: String) {
        viewModelScope.launch {
            val res = repository.login(LoginDto(kakaoToken))
            _userInfoLiveData.value = res.result
        }
    }

    fun nicknameValidation(nickname: String) {
        viewModelScope.launch {
            val res = repository.nicknameValidate(nickname)
            _nicknameValidationLiveData.value = res.result
        }
    }

    fun signup(context: Context, imageUri: Uri?, grade: Int, nickname: String, kakaoToken: String) {
        viewModelScope.launch {
            val res = repository.signup(context, imageUri, grade, nickname, kakaoToken)
            _signupResponseLiveData.value = res.result
        }
    }
}