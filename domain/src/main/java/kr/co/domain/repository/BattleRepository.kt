package kr.co.domain.repository

import android.content.ContentValues
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.BattleRequest
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentsResponse
import kr.co.domain.model.ProfileImageResponse
import kr.co.domain.model.WaitingBattleResponse
import retrofit2.http.Path

interface BattleRepository {
    suspend fun getWaitingBattleInfo(userId: Long, limit: Int, page: Int) : WaitingBattleResponse
    suspend fun getThreeKeywords(userId: Long): KeywordsResponse
    suspend fun getOpponentsList(userId: Long): OpponentsResponse
    suspend fun getMyProfileImage(userId: Long): ProfileImageResponse
    suspend fun sendBattleRequest(userId: Long, opponentId: Long, wordId: Long, sentence: String, duration: Int): BattleRequestResponse
    suspend fun getMyBattleProgressInfo() : List<MyBattleProgressDto>
    suspend fun getMyBattleCompletionInfo() : List<MyBattleCompletionDto>
    suspend fun getMyBattleProgMoreInfo() : MyBattleProgMoreDto
    suspend fun getMyBattleCompMoreInfo() : MyBattleCompMore
    suspend fun getAllBattleProgressInfo() : AllBattleProgress
    suspend fun getAllBattleCompletionInfo() : AllBattleCompletion
    suspend fun getAllBattleProgMoreInfo() : AllBattleProgMore
    suspend fun getAllBattleCompMoreInfo() : AllBattleCompMore
}