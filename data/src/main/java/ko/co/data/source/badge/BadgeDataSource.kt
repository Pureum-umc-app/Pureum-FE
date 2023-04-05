package ko.co.data.source.badge

import android.content.ContentValues
import android.util.Log
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.BadgeInfo
import kr.co.domain.model.BadgeResponse
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.SaveBadgeRequest
import javax.inject.Inject

class BadgeDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun saveBadge(saveBadgeReq: SaveBadgeRequest, userId: Long): DefaultResponse {
        var response = DefaultResponse(0, false, "saveBadge Failed", "saveBadge Failed")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.saveBadge(saveBadgeReq, userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "saveBadge Failed: $it")
            }
        }
        return response
    }
    suspend fun getBadges(userId: Long): BadgeInfo {
        var response = BadgeResponse(0, false, "getBadges Failed",
            BadgeInfo(emptyList(), 0))
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getBadges(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "getBadges Failed: $it")
            }
        }
        return response.result
    }
}