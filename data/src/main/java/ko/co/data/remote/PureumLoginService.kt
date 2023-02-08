package ko.co.data.remote

import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.NicknameValidationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PureumLoginService {
    // 로그인 API
    @POST("/users/signin")
    suspend fun login(@Body loginDto: LoginDto) : LoginResponse

    // 닉네임 유효성 체크 API
    @GET("/users/nickname/{nickname}/validation")
    suspend fun nicknameValidate(@Path("nickname") nickname: String) : NicknameValidationResponse
}