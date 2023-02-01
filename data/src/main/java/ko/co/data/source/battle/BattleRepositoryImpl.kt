package ko.co.data.source.battle

import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.WaitingBattleDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

class BattleRepositoryImpl @Inject constructor(
    private val dataSource: BattleDateSource
) : BattleRepository{
    override suspend fun getWaitingBattleInfo() : List<WaitingBattleDto> =
        dataSource.getWaitingBattleInfo()

    override suspend fun getMyBattleProgressInfo(): List<MyBattleProgressDto> =
        dataSource.getMyBattleProgressInfo()
}