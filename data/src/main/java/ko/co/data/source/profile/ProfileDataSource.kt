package ko.co.data.source.profile

import android.content.ContentValues.TAG
import android.util.Log
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.ProfileInfo
import kr.co.domain.model.ProfileInfoResponse
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val pureumService: PureumService
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
}