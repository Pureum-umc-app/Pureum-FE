package ko.co.data.remote

import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.LoginDto
import kr.co.domain.model.LoginResponse
import kr.co.domain.model.SignupResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PureumLoginService {
    // 로그인 API
    @POST("/users/signin")
    suspend fun login(@Body loginDto: LoginDto) : LoginResponse

    // 닉네임 유효성 체크 API
    @GET("/users/nickname/{nickname}/validation")
    suspend fun nicknameValidate(@Path("nickname") nickname: String) : DefaultResponse

    // 회원가입 API
    @Multipart
    @POST("/users/signup")
    suspend fun signup(
        @Part image: MultipartBody.Part?,
        @Part("data") data: RequestBody
    ) : SignupResponse
}