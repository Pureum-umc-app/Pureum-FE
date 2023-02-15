package ko.co.data.source.attendances

import android.content.ContentValues
import android.util.Log
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.*
import javax.inject.Inject

class AttendancesDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getStampList(userId: Long) : StampListResponse {
        var response = StampListResponse(code = 0, isSuccess = true, message = "요청에 성공하였습니다.",
            result = StampInfo(
                accumulatedCnt = 50, currentCnt = 2, userId = 1
            )
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getStampList(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "Stamp List Failed")
            }
        }
        return response
    }

    suspend fun attendanceCheck(userId: UserId) : AttendancesCheckResponse {
        var response = AttendancesCheckResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다.", result = CheckId(
                check_id = 2
            )
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.attendanceCheck(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "Attendance Check Failed")
            }
        }
        return response
    }
}