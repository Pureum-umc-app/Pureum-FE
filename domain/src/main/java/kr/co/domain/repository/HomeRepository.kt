package kr.co.domain.repository

import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.HomeResponse
import kr.co.domain.model.SetUsageTimeReq

interface HomeRepository {
    suspend fun getHomeInfo(userId: Long): HomeResponse
    suspend fun setPurposeTime(userId: Long, purposeTime: Int): DefaultResponse
}