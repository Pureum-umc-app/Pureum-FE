package ko.co.data.remote

import kr.co.domain.model.*
import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.BattleRequest
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.BattleControlResponse
import kr.co.domain.model.BattleId
import kr.co.domain.model.BattleLike
import kr.co.domain.model.BattleLikeReq
import kr.co.domain.model.DailyRecord
import kr.co.domain.model.DailyRecordResponse
import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.GradeResponse
import kr.co.domain.model.HomeResponse
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.OpponentsResponse
import kr.co.domain.model.ProfileImageResponse
import kr.co.domain.model.MyBattleCompletion
import kr.co.domain.model.MyBattleProgMore
import kr.co.domain.model.MyBattleProgress
import kr.co.domain.model.ProfileInfoResponse
import kr.co.domain.model.RankResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SetUsageTimeReq
import kr.co.domain.model.SentencesListResponse
import kr.co.domain.model.TodayKeywordResponse
import kr.co.domain.model.WaitingBattleResponse
import kr.co.domain.model.WriteSentencesReq
import kr.co.domain.model.WriteSentencesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface PureumService {
    // 홈 화면 리스트 반환 API
    @GET("uses/{userId}")
    suspend fun getHomeInfo(@Path("userId") userId: Long) : HomeResponse
    // 목표 사용 시간 설정 API
    @POST("/uses/{userId}/set-usage-time")
    suspend fun setPurposeTime(@Path("userId") userId: Long, @Body setUsageTimeReq: SetUsageTimeReq) : DefaultResponse
    // 일일 사용 시간, 휴대폰 켠 횟수 저장
    @POST("/uses/{userId}/use-time-and-count")
    suspend fun commitDailyRecord(@Path("userId") userId: Long, @Body postUseTimeAndCountReq: DailyRecord) : DailyRecordResponse

    // 나의 학년 카테고리 반환 API
    @GET("/uses/{userId}/grade")
    suspend fun getMyGrade(@Path("userId") userId: Long) : GradeResponse
    // 날짜 별 랭킹(전체) 조회 API
    @GET("/uses/rank-all-grade")
    suspend fun getAllRankList(@Query("date") date: String, @Query("page") page: Int): RankResponse
    // 날짜 별 랭킹(같은 카테고리) 조회 API
    @GET("/uses/rank-same-grade")
    suspend fun getSameRankList(@Query("date") date: String, @Query("page") page: Int): RankResponse

    // 대기 중인 대결 리스트 반환
    @GET("/battles/wait-list/{userId}")
    suspend fun getWaitingBattleInfo(@Path("userId") userId: Long, @Query("limit") limit: Int, @Query("page") page: Int): WaitingBattleResponse
    // 대결 수락 API
    @POST("/battles/accept")
    suspend fun acceptBattle(@Body battleId: BattleId): BattleControlResponse
    // 대결 거절 API
    @POST("battles/reject")
    suspend fun refuseBattle(@Body battleId: BattleId): BattleControlResponse
    // 대결 취소 API
    @POST("battles/cancel")
    suspend fun cancelBattle(@Body battleId: BattleId): BattleControlResponse

    // 대결 키워드 3개 반환 API
    @GET("/battles/{userId}/battle-word")
    suspend fun getThreeKeywords(@Path("userId") userId: Long): KeywordsResponse
    // 대결 상대 리스트 반환 API
    @GET("/battles/{userId}/fighters")
    suspend fun getOpponentsList(@Path("userId") userId: Long): OpponentsResponse
    // 대결 신청 시 내 사진 조회
    @GET("/battles/apply/photo/{userId}")
    suspend fun getMyProfileImage(@Path("userId") userId: Long): ProfileImageResponse
    // 대결 신청 API
    @POST("/battles")
    suspend fun sendBattleRequest(@Body battleRequest: BattleRequest): BattleRequestResponse
    // 대결 받은 사람 대결 문장 작성 API
    @POST("/battles/challenged/write")
    suspend fun writeSentence(@Body battleSentence: BattleSentenceRequest): BattleSentenceResponse

    //오늘의 키워드 반환
    @GET("/sentences/word/{userId}")
    suspend fun todayKeyword(@Path("userId")userId: Long) : TodayKeywordResponse
    // 오늘의 작성 전 단어 반환
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
        @Query("word_id")word_id: Long
    ) : SentencesListResponse
    @POST("/sentences/write")
    suspend fun sentencesWrite(@Body writeSentencesReq: WriteSentencesReq) : WriteSentencesResponse

    // 프로필 조회 API
    @GET("/mypages/{userId}")
    suspend fun getProfileInfo(@Path("userId") userId: Long) : ProfileInfoResponse
    // 회원 탈퇴 API
    @PATCH("/users/resign/{userId}")
    suspend fun withdrawal(@Path("userId") userId: Long) : DefaultResponse
    // 프로필 수정 API
    @Multipart
    @PATCH("/mypages/{userId}")
    suspend fun editProfile(
        @Path("userId") userId: Long,
        @Part image: MultipartBody.Part?,
        @Part("nickname") nickname: RequestBody
    ) : DefaultResponse
    // my 문장 리스트 API
    @GET("/mypages/sentence")
    suspend fun getMySentenceList() : MySentencesListResponse

    // 진행 중인 대결 리스트 반환 API
    @GET("/battles/list")
    suspend fun getAllBattleProgressInfo(
        @Query("limit")limit: Int,
        @Query("page")page: Int
    ) : AllBattleProgress

    // 종료된 대결 리스트 반환 API
    @GET("/battles/complete-list")
    suspend fun getAllBattleCompletionInfo(
        @Query("limit")limit: Int,
        @Query("page")page: Int
    ) : AllBattleCompletion

    // MY 진행 중인 대결 리스트 반환 API
    @GET("/battles/list/{userId}")
    suspend fun getMyBattleProgressInfo(
        @Path("userId") userId: Long,
        @Query("limit")limit: Int,
        @Query("page")page: Int
    ) : MyBattleProgress

    // MY 종료된 대결 리스트 반환 API
    @GET("/battles/complete-list/{userId}")
    suspend fun getMyBattleCompletionInfo(
        @Path("userId") userId: Long,
        @Query("limit")limit: Int,
        @Query("page")page: Int
    ) : MyBattleCompletion

    // 전체 대결 정보 반환 API (진행 중, 대기 중)
    @GET("battles/run/{battleId}")
    suspend fun getAllBattleProgMoreInfo(
        @Path("battleId") battleId: Long
    ) : AllBattleProgMore

    // 전체 대결 정보 반환 API (완료)
    @GET("/battles/finish/{battleIdx}")
    suspend fun getAllBattleCompMoreInfo(
        @Path("battleIdx") battleId: Long
    ) : AllBattleCompMore

    // MY 대결 정보 반환 (진행 중, 대기 중)
    @GET("/battles/run/my/{battleIdx}")
    suspend fun getMyBattleProgMoreInfo(
        @Path("battleIdx") battleId: Long
    ) : MyBattleProgMore

    // MY 대결 정보 반환 (완료)
    @GET("/battles/finish/my/{battleIdx}")
    suspend fun getMyBattleCompMoreInfo(
        @Path("battleIdx") battleId: Long
    ) : MyBattleCompMore

    // 대결 문장 좋아요 API
    @POST("/battles/like")
    suspend fun postBattleLike(
        @Body request: BattleLikeReq
    ) : BattleLike

    //도장 개수 반환 API
    @GET("/attendances/{userId}")
    suspend fun getStampList(@Path("userId") userId: Long) : StampListResponse
    //출석 체크 API
    @POST("/attendances/check")
    suspend fun attendanceCheck(@Body userId: UserId) : AttendancesCheckResponse

    //문장 삭제 API
    suspend fun deleteMySentence(@Path("sentenceId") sentenceId: Long) : DefaultResponse
}