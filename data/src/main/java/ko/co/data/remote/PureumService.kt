package ko.co.data.remote

import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.HomeResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SetUsageTimeReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PureumService {
    // 홈 화면 리스트 반환 API
    @GET("uses/{userId}")
    suspend fun getHomeInfo(@Path("userId") userId: Long) : HomeResponse
    // 목표 사용 시간 설정 API
    @POST("/uses/{userId}/set-usage-time")
    suspend fun setPurposeTime(@Path("userId") userId: Long, @Body setUsageTimeReq: SetUsageTimeReq) : DefaultResponse

    // 오늘의 작성 전 단어 반환
    @GET("/sentences/incomplete/{userId}")
    suspend fun sentencesIncomplete(userId: Int) : SentencesIncompleteResponse
    @GET("/sentences/complete/{userId}")
    suspend fun sentencesComplete(userId: Int) : SentencesIncompleteResponse
}