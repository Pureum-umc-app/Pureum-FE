package ko.co.data.remote

import kr.co.domain.model.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PureumService {
    // 홈 화면 리스트 반환 API
    @GET("uses/{userId}")
    suspend fun getHomeInfo(@Path("userId") userId: Long) : HomeResponse
    // 목표 사용 시간 설정 API
    @POST("/uses/{userId}/set-usage-time")
    suspend fun setPurposeTime(@Path("userId") userId: Long, @Body setUsageTimeReq: SetUsageTimeReq) : DefaultResponse

    // 오늘의 작성 전 단어 반환 API
    @GET("/sentences/incomplete/{userId}")
    suspend fun sentencesIncomplete(@Path("userId")userId: Long) : SentencesIncompleteResponse
    // 오늘의 작성 후 단어 반환 API
    @GET("/sentences/complete/{userId}")
    suspend fun sentencesComplete(@Path("userId")userId: Long) : SentenceCompleteResponse
    // 단어별 문장 리스트 반환 API
    @GET("/sentences/{userId}")
    suspend fun sentencesList(
        @Query("limit")limit: Int,
        @Query("page")page: Int,
        @Query("sort")sort: String,
        @Path("userId")userId: Long,
        @Query("word_id")word_id: Int
    ) : SentencesListResponse
    @POST("/sentences/write")
    suspend fun sentencesWrite(@Body writeSentencesReq: WriteSentencesReq) : WriteSentencesResponse
}