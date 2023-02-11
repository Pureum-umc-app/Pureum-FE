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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
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
        var nicknameValidationResponse = NicknameValidationResponse(0, false, "", "")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumLoginService.nicknameValidate(nickname)
            }.onSuccess {
                nicknameValidationResponse = it
            }.onFailure {
                Log.e(TAG, "Nickname Validation Failed")
            }
        }
        return nicknameValidationResponse
    }

    suspend fun signup(imageFile: File?, grade: Int, nickname: String, kakaoToken: String) : SignupResponse {
        val image = if (imageFile != null) {
            val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
        } else {
            null
        }

        val createUserDto = CreateUserDto(grade.toString(), nickname, kakaoToken)

        val mediaType = "text/plain".toMediaTypeOrNull()
//        val body = MultipartBody.Builder().setType(MultipartBody.FORM)
//            .addFormDataPart("image", imagePath, File(imagePath).asRequestBody("application/octet-stream".toMediaTypeOrNull()))
//            .addFormDataPart("data", null, createUserDto.toString().toRequestBody("application/json".toMediaTypeOrNull()))
//            .build()

        val json = JSONObject("{\"grade\":\"$grade\",\"kakaoToken\":\"$kakaoToken\",\"nickname\":\"$nickname\"}").toString()
        val jsonBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        var signupResponse = SignupResponse(0, false, "", "")
        withContext(Dispatchers.IO) {
//            signupResponse = pureumLoginService.signup(image, createUserDto)
            runCatching {
                pureumLoginService.signup(image, jsonBody)
            }.onSuccess {
                signupResponse = it
            }.onFailure {
                Log.e(TAG, "Signup Failed: $it")
            }
        }
        return signupResponse
    }
}