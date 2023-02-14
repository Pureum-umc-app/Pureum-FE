package kr.co.domain.repository

import android.content.Context
import android.net.Uri
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.MySentencesListResponse
import kr.co.domain.model.ProfileInfoResponse

interface ProfileRepository {
    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse
    suspend fun withdrawal(userId: Long) : DefaultResponse
    suspend fun nicknameValidate(nickname: String) : DefaultResponse
    suspend fun editProfile(userId: Long, context: Context, imageUri: Uri?, nickname: String) : DefaultResponse
    suspend fun getMySentenceList(): MySentencesListResponse
}