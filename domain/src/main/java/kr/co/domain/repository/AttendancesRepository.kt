package kr.co.domain.repository

import kr.co.domain.model.AttendancesCheckResponse
import kr.co.domain.model.StampListResponse
import kr.co.domain.model.UserId

interface AttendancesRepository {
    suspend fun getStampList(userId: Long) : StampListResponse
    suspend fun attendancesCheck(userId: UserId) : AttendancesCheckResponse
}