package ko.co.data.remote

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SentencesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PureumService {
    // 오늘의 작성 전 단어 반환
    @GET("/sentences/incomplete/{userId}")
    suspend fun sentencesIncomplete(@Path("userId")userId: Int) : SentencesIncompleteResponse
    @GET("/sentences/complete/{userId}")
    suspend fun sentencesComplete(@Path("userId")userId: Int) : SentenceCompleteResponse
    @GET("/sentences/{userId}")
    suspend fun sentencesList(
        @Query("limit")limit: Int,
        @Query("page")page: Int,
        @Query("sort")sort: String,
        @Path("userId")userId: Int,
        @Query("word_id")word_id: Int
    ) : SentencesListResponse
}