package ko.co.data.source.battle

import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentDto
import kr.co.domain.model.WaitingBattleDto
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

class BattleRepositoryImpl @Inject constructor(
    private val dataSource: BattleDateSource
) : BattleRepository{
    override suspend fun getWaitingBattleInfo() : List<WaitingBattleDto> =
        dataSource.getWaitingBattleInfo()

    override suspend fun getThreeKeywords(): List<String> =
        dataSource.getThreeKeywords()

    override suspend fun getMyBattleProgressInfo(): List<MyBattleProgressDto> =
        dataSource.getMyBattleProgressInfo()

    override suspend fun getMyBattleCompletionInfo(): List<MyBattleCompletionDto> =
        dataSource.getMyBattleCompletion()

    override suspend fun getDefinition(keyword: String): String =
        dataSource.getDefinition(keyword)

    override suspend fun getOpponentsList(): List<OpponentDto> =
        dataSource.getOpponentsList()

    override suspend fun getAdditionalOpponents(position: Int, itemCount: Int): List<OpponentDto> =
        dataSource.getAdditionalOpponents(position, itemCount)

    override suspend fun getMyBattleProgMoreInfo(): MyBattleProgMoreDto =
        dataSource.getMyBattleProgMoreInfo()

    override suspend fun getMyBattleCompMoreInfo(): MyBattleCompMore =
        dataSource.getMyBattleCompMoreInfo()
}