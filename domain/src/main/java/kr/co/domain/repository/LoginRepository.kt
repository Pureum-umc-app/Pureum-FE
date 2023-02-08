package kr.co.domain.repository

import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse

interface LoginRepository {
    suspend fun login(loginDto: LoginDto) : LoginResponse
    suspend fun nicknameValidate(nickname: String) : NicknameValidationResponse
}