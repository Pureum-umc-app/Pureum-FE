package kr.co.domain.repository

import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentDto
import kr.co.domain.model.WaitingBattleDto

interface BattleRepository {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto>
    suspend fun getThreeKeywords() : List<String>
    suspend fun getMyBattleProgressInfo() : List<MyBattleProgressDto>
    suspend fun getMyBattleCompletionInfo() : List<MyBattleCompletionDto>
    suspend fun getDefinition(keyword: String) : String
    suspend fun getOpponentsList() : List<OpponentDto>
    suspend fun getAdditionalOpponents(position: Int, itemCount: Int) : List<OpponentDto>
    suspend fun getMyBattleProgMoreInfo() : MyBattleProgMoreDto
}