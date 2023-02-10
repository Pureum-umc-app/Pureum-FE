package ko.co.data.source.login

import android.content.ContentValues.TAG
import android.util.Log
import ko.co.data.remote.PureumLoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.CreateUserDto
import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginJwtToken
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse
import kr.co.domain.model.SignupResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
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

    suspend fun signup(imagePath: String, grade: Int, nickname: String, kakaoToken: String) : SignupResponse {
        val image = if (imagePath != "null") {
            val file = File(imagePath)
            val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestFile)
        } else {
            null
        }

        val createUserDto = CreateUserDto(grade.toString(), nickname, kakaoToken).toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val hashMap = hashMapOf<String, String>()
        hashMap["grade"] = grade.toString()
        hashMap["nickname"] = nickname
        hashMap["kakaoToken"] = kakaoToken

//        val hashMap = hashMapOf<String, RequestBody>()
//        hashMap["grade"] = grade.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//        hashMap["nickname"] = nickname.toRequestBody("text/plain".toMediaTypeOrNull())
//        hashMap["kakaoToken"] = kakaoToken.toRequestBody("text/plain".toMediaTypeOrNull())

        var signupResponse = SignupResponse(0, false, "", "")
        withContext(Dispatchers.IO) {
//            signupResponse = pureumLoginService.signup(image, createUserDto)
            runCatching {
                pureumLoginService.signup(image, hashMap.toString().toRequestBody("application/json".toMediaTypeOrNull()))
            }.onSuccess {
                signupResponse = it
            }.onFailure {
                Log.e(TAG, "Signup Failed: $it")
            }
        }
        return signupResponse
    }
}