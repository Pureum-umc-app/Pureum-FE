package kr.co.pureum.views.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.repository.LoginRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private var _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String>
        get() = _responseMessage

    fun nicknameValidation(nickname: String) {
        viewModelScope.launch {
            val res = repository.nicknameValidate(nickname).result
            _responseMessage.value = res
        }
    }
}