package ko.co.data.source.profile

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import ko.co.data.remote.PureumLoginService
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.CreateUserDto
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.NicknameValidationResponse
import kr.co.domain.model.ProfileInfo
import kr.co.domain.model.ProfileInfoResponse
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

class ProfileDataSource @Inject constructor(
    private val pureumService: PureumService,
    private val pureumLoginService: PureumLoginService
) {
    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse {
        var response = ProfileInfoResponse(0, false, "getProfileInfo Failed",
            ProfileInfo(0, "nickname error", "profileUrl error")
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getProfileInfo(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getProfileInfo Failed: $it")
            }
        }
        return response
    }

    suspend fun withdrawal(userId: Long) : DefaultResponse {
        var response = DefaultResponse(0, false, "withdrawal Failed", "error")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.withdrawal(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "withdrawal Failed: $it")
            }
        }
        return response
    }

    suspend fun nicknameValidate(nickname: String) : DefaultResponse {
        var response = DefaultResponse(0, false, "", "중복된 닉네임입니다.")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumLoginService.nicknameValidate(nickname)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "Nickname Validation Failed")
            }
        }
        return response
    }

    suspend fun editProfile(userId: Long, context: Context, imageUri: Uri?, nickname: String) : DefaultResponse {
        val body = imageUri?.let {
            val file = toFile(context, it)
            MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", file.name, file.asRequestBody("application/octet-stream".toMediaTypeOrNull()))
                .addFormDataPart("data", null, nickname.toRequestBody("application/json".toMediaTypeOrNull()))
                .build()
        }
        val nicknameRequestBody = nickname.toRequestBody("text/plain".toMediaTypeOrNull())

        var response = DefaultResponse(0, false, "", "")
        withContext(Dispatchers.IO) {
            runCatching {
                body?.let {
                    pureumService.editProfile(userId, it.part(0), nicknameRequestBody)
                }.run {
                    pureumService.editProfile(userId, null, nicknameRequestBody)
                }
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "Edit Profile Failed: $it")
            }
        }
        return response
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
}