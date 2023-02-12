package kr.co.domain.repository

import android.content.Context
import android.net.Uri
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.SignupResponse

interface LoginRepository {
    suspend fun login(loginDto: LoginDto) : LoginResponse
    suspend fun nicknameValidate(nickname: String) : DefaultResponse
    suspend fun signup(context: Context, imageUri: Uri?, grade: Int, nickname: String, kakaoToken: String) : SignupResponse
}