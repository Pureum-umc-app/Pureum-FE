package ko.co.data.remote

import kr.co.domain.model.NicknameValidationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PureumLoginService {
    // 닉네임 유효성 체크 API
    @GET("/user/nickname/{nickname}/validation")
    suspend fun nicknameValidate(@Path("nickname") nickname: String) : NicknameValidationResponse
}