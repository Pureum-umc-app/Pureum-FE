package kr.co.domain.repository

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.BattleControlResponse
import kr.co.domain.model.BattleId
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletion
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.OpponentsResponse
import kr.co.domain.model.ProfileImageResponse
import kr.co.domain.model.WaitingBattleResponse
import kr.co.domain.model.MyBattleProgress

interface BattleRepository {
    suspend fun getWaitingBattleInfo(userId: Long, limit: Int, page: Int) : WaitingBattleResponse
    suspend fun acceptBattle(battleId: BattleId): BattleControlResponse
    suspend fun refuseBattle(battleId: BattleId): BattleControlResponse
    suspend fun cancelBattle(battleId: BattleId): BattleControlResponse

    suspend fun getThreeKeywords(userId: Long): KeywordsResponse
    suspend fun getOpponentsList(userId: Long): OpponentsResponse
    suspend fun getMyProfileImage(userId: Long): ProfileImageResponse
    suspend fun sendBattleRequest(userId: Long, opponentId: Long, wordId: Long, sentence: String, duration: Int): BattleRequestResponse

    suspend fun getMyBattleProgressInfo(userId: Long) : MyBattleProgress
    suspend fun getMyBattleCompletionInfo(userId: Long) : MyBattleCompletion
    suspend fun getMyBattleProgMoreInfo() : MyBattleProgMoreDto
    suspend fun getMyBattleCompMoreInfo() : MyBattleCompMore
    suspend fun getAllBattleProgressInfo() : AllBattleProgress
    suspend fun getAllBattleCompletionInfo() : AllBattleCompletion
    suspend fun getAllBattleProgMoreInfo(itemIdx: Long) : AllBattleProgMore
    suspend fun getAllBattleCompMoreInfo(itemIdx: Long) : AllBattleCompMore
}