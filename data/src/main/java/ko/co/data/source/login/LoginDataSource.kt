package ko.co.data.source.login

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import ko.co.data.remote.PureumLoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.CreateUserDto
import kr.co.domain.model.LoginDto
import kr.co.domain.model.UserInfo
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse
import kr.co.domain.model.SignupResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val pureumLoginService: PureumLoginService
) {
    suspend fun login(loginDto: LoginDto) : LoginResponse {
        var loginResponse = LoginResponse(0, false, "", UserInfo("error", 0L))
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

    suspend fun signup(context: Context, imageUri: Uri?, grade: Int, nickname: String, kakaoToken: String) : SignupResponse {
        val createUserDto = CreateUserDto(grade.toString(), nickname, kakaoToken)
        val body = imageUri?.let {
            val file = setImgFromUri(context, it)
            MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", file.name, file.asRequestBody("application/octet-stream".toMediaTypeOrNull()))
                .addFormDataPart("data", null, createUserDto.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                .build()
        }

        val json = JSONObject("{\"grade\":\"$grade\",\"kakaoToken\":\"$kakaoToken\",\"nickname\":\"$nickname\"}").toString()
        val jsonBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        var signupResponse = SignupResponse(0, false, "", "")
        withContext(Dispatchers.IO) {
            runCatching {
                body?.let {
                    pureumLoginService.signup(it.part(0), jsonBody)
                }.run {
                    pureumLoginService.signup(null, jsonBody)
                }
            }.onSuccess {
                signupResponse = it
            }.onFailure {
                Log.e(TAG, "Signup Failed: $it")
            }
        }
        return signupResponse
    }

    // URI -> File
    private fun toFile(context: Context, imageUri: Uri): File {
        val fileName = getFileName(context, imageUri)

        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(storageDir, fileName)

        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(4 * 1024)
        while (true) {
            val byteCount = inputStream!!.read(buffer)
            if (byteCount < 0) break
            outputStream.write(buffer, 0, byteCount)
        }
        outputStream.flush()

        return File(file.absolutePath)
    }

    // get file name & extension
    private fun getFileName(context: Context, imageUri: Uri): String {
        val name = imageUri.toString().split("/").last()
        val ext = context.contentResolver.getType(imageUri)!!.split("/").last()

        return "$name.$ext"
    }

    private fun setImgFromUri(context: Context, imageUri: Uri): File {
//        if (Build.VERSION.SDK_INT < 28) {
//            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
//        } else {
//            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
//            ImageDecoder.decodeBitmap(source)
//        }
        return toFile(context, imageUri)
    }
}