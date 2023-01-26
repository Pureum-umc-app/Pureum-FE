package kr.co.domain.repository

import kr.co.domain.model.NicknameValidationResponse

interface LoginRepository {
    suspend fun nicknameValidate(nickname: String) : NicknameValidationResponse
}