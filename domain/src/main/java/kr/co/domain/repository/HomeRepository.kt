package kr.co.domain.repository

import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.HomeResponse

interface HomeRepository {
    suspend fun getHomeInfo(): HomeResponse
    suspend fun updateGoalTime(goalTime: Int): DefaultResponse
}