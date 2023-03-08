package kr.co.domain.repository

import android.content.Context
import android.net.Uri
import kr.co.domain.model.*

interface ProfileRepository {
    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse
    suspend fun withdrawal(userId: Long) : DefaultResponse
    suspend fun nicknameValidate(nickname: String) : DefaultResponse
    suspend fun editProfile(userId: Long, context: Context, imageUri: Uri?, nickname: String) : DefaultResponse
    suspend fun getMySentenceList(): MySentencesListResponse
    suspend fun deleteMySentence(sentenceId: Long): DefaultResponse
    suspend fun modifyMySentence(sentence: String, sentenceId: Long) : DefaultResponse
    suspend fun mySentenceInfo(sentenceId: Long) : MySentenceInfoResponse
}