package kr.co.domain.repository

import kr.co.domain.model.DailyRecord
import kr.co.domain.model.DailyRecordResponse
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.HomeResponse
import kr.co.domain.model.SetUsageTimeReq
import retrofit2.http.Body
import retrofit2.http.Path

interface HomeRepository {
    suspend fun getHomeInfo(userId: Long): HomeResponse
    suspend fun setPurposeTime(userId: Long, purposeTime: Int): DefaultResponse
    suspend fun commitDailyRecord(userId: Long, postUseTimeAndCountReq: DailyRecord) : DailyRecordResponse
}