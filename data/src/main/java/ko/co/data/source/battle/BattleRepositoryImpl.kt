package ko.co.data.source.battle

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletion
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgress
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

    override suspend fun getMyBattleProgressInfo(userId: Long): MyBattleProgress =
        dataSource.getMyBattleProgressInfo(userId)

    override suspend fun getMyBattleCompletionInfo(userId: Long): MyBattleCompletion =
        dataSource.getMyBattleCompletion(userId)

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

    override suspend fun getAllBattleProgressInfo(): AllBattleProgress =
        dataSource.getAllBattleProgressInfo()

    override suspend fun getAllBattleCompletionInfo(): AllBattleCompletion =
        dataSource.getAllBattleCompletionInfo()

    override suspend fun getAllBattleProgMoreInfo(): AllBattleProgMore =
        dataSource.getAllBattleProgMoreInfo()

    override suspend fun getAllBattleCompMoreInfo(): AllBattleCompMore =
        dataSource.getAllBattleCompMoreInfo()
}