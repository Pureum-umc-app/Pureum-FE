package ko.co.data.source.login

import android.content.ContentValues.TAG
import android.util.Log
import ko.co.data.remote.PureumLoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginJwtToken
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val pureumLoginService: PureumLoginService
) {
    suspend fun login(loginDto: LoginDto) : LoginResponse {
        var loginResponse = LoginResponse(0, false, "", LoginJwtToken("error"))
        withContext(Dispatchers.IO) {
            runCatching {
                pureumLoginService.login(loginDto)
            }.onSuccess {
                loginResponse = it
            }.onFailure {
                Log.e(TAG, "Login Failure")
            }
        }
        return loginResponse
    }

    suspend fun nicknameValidate(nickname: String) : NicknameValidationResponse {
        val nicknameValidationResponse = NicknameValidationResponse(0, false, "", "")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumLoginService.nicknameValidate(nickname)
            }.onSuccess {
                with(nicknameValidationResponse) {
                    code = it.code
                    isSuccess = it.isSuccess
                    message = it.message
                    result = it.result
                }
            }.onFailure {
                Log.e(TAG, "Nickname Validation Failed")
            }
        }
        return nicknameValidationResponse
    }
}