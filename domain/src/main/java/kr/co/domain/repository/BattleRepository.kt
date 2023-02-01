package kr.co.domain.repository

import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.WaitingBattleDto

interface BattleRepository {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto>
    suspend fun getThreeKeywords() : List<String>
    suspend fun getMyBattleProgressInfo() : List<MyBattleProgressDto>
}