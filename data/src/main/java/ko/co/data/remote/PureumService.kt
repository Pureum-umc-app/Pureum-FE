package ko.co.data.remote

import kr.co.domain.model.SentencesIncompleteResponse
import retrofit2.http.GET

interface PureumService {
    // 오늘의 작성 전 단어 반환
    @GET("/sentences/incomplete/{userId}")
    suspend fun sentencesIncomplete(userId: Int) : SentencesIncompleteResponse
    @GET("/sentences/complete/{userId}")
    suspend fun sentencesComplete(userId: Int) : SentencesIncompleteResponse
}