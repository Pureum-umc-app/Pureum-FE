package kr.co.domain.repository

import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.ProfileInfoResponse
import retrofit2.http.Path

interface ProfileRepository {
    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse
    suspend fun withdrawal(userId: Long) : DefaultResponse
}