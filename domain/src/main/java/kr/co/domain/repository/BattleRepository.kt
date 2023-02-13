package kr.co.domain.repository

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentDto
import kr.co.domain.model.WaitingBattle
import kr.co.domain.model.WaitingBattleResponse

interface BattleRepository {
    suspend fun getWaitingBattleInfo(userId: Long, limit: Int, page: Int) : WaitingBattleResponse
    suspend fun getThreeKeywords() : List<String>
    suspend fun getMyBattleProgressInfo() : List<MyBattleProgressDto>
    suspend fun getMyBattleCompletionInfo() : List<MyBattleCompletionDto>
    suspend fun getDefinition(keyword: String) : String
    suspend fun getOpponentsList() : List<OpponentDto>
    suspend fun getAdditionalOpponents(position: Int, itemCount: Int) : List<OpponentDto>
    suspend fun getMyBattleProgMoreInfo() : MyBattleProgMoreDto
    suspend fun getMyBattleCompMoreInfo() : MyBattleCompMore
    suspend fun getAllBattleProgressInfo() : AllBattleProgress
    suspend fun getAllBattleCompletionInfo() : AllBattleCompletion
    suspend fun getAllBattleProgMoreInfo() : AllBattleProgMore
    suspend fun getAllBattleCompMoreInfo() : AllBattleCompMore
}