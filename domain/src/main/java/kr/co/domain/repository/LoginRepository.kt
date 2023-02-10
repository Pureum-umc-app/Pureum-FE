package kr.co.domain.repository

import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse
import kr.co.domain.model.SignupResponse
import java.io.File

interface LoginRepository {
    suspend fun login(loginDto: LoginDto) : LoginResponse
    suspend fun nicknameValidate(nickname: String) : NicknameValidationResponse
    suspend fun signup(imagePath: String, grade: Int, nickname: String, kakaoToken: String) : SignupResponse
}