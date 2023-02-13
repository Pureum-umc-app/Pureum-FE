package kr.co.domain.repository

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletion
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgress
import kr.co.domain.model.OpponentDto
import kr.co.domain.model.WaitingBattleDto

interface BattleRepository {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto>
    suspend fun getThreeKeywords() : List<String>
    suspend fun getMyBattleProgressInfo(userId: Long) : MyBattleProgress
    suspend fun getMyBattleCompletionInfo(userId: Long) : MyBattleCompletion
    suspend fun getDefinition(keyword: String) : String
    suspend fun getOpponentsList() : List<OpponentDto>
    suspend fun getAdditionalOpponents(position: Int, itemCount: Int) : List<OpponentDto>
    suspend fun getMyBattleProgMoreInfo() : MyBattleProgMoreDto
    suspend fun getMyBattleCompMoreInfo() : MyBattleCompMore
    suspend fun getAllBattleProgressInfo() : AllBattleProgress
    suspend fun getAllBattleCompletionInfo() : AllBattleCompletion
    suspend fun getAllBattleProgMoreInfo(itemIdx: Long) : AllBattleProgMore
    suspend fun getAllBattleCompMoreInfo() : AllBattleCompMore
}