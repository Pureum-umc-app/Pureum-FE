package kr.co.domain.repository

import kr.co.domain.model.WaitingBattleDto

interface BattleRepository {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto>
}