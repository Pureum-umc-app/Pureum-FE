package ko.co.data.source.login

import kr.co.domain.model.NicknameValidationResponse
import kr.co.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
) : LoginRepository {
    override suspend fun nicknameValidate(nickname: String): NicknameValidationResponse {
        return dataSource.nicknameValidate(nickname)
    }
}