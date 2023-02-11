package ko.co.data.source.login

import android.content.Context
import android.net.Uri
import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse
import kr.co.domain.model.SignupResponse
import kr.co.domain.repository.LoginRepository
import java.io.File
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
) : LoginRepository {
    override suspend fun login(loginDto: LoginDto): LoginResponse =
        dataSource.login(loginDto)

    override suspend fun nicknameValidate(nickname: String): NicknameValidationResponse =
        dataSource.nicknameValidate(nickname)

    override suspend fun signup(context: Context, imageUri: Uri?, grade: Int, nickname: String, kakaoToken: String) : SignupResponse =
        dataSource.signup(context, imageUri, grade, nickname, kakaoToken)
}