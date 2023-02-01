package kr.co.domain.repository

import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.WaitingBattleDto

interface BattleRepository {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto>
<<<<<<< HEAD
    suspend fun getThreeKeywords() : List<String>
=======

    suspend fun getMyBattleProgressInfo() : List<MyBattleProgressDto>
>>>>>>> 7cb8cb5e352cf707e5663c295d1ed7bbc3124116
}