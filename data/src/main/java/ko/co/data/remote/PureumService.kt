package ko.co.data.remote

import kr.co.domain.model.HomeResponse
import kr.co.domain.model.SentencesIncompleteResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PureumService {
    // 홈 화면 리스트 반환 API
    @GET("uses/{userId}")
    suspend fun getHomeInfo(@Path("userId") userId: Long) : HomeResponse

    // 오늘의 작성 전 단어 반환
    @GET("/sentences/incomplete/{userId}")
    suspend fun sentencesIncomplete(userId: Int) : SentencesIncompleteResponse
    @GET("/sentences/complete/{userId}")
    suspend fun sentencesComplete(userId: Int) : SentencesIncompleteResponse
}