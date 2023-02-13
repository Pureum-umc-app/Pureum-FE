package ko.co.data.source.battle

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentsResponse
import kr.co.domain.model.WaitingBattleResponse
import kr.co.domain.repository.BattleRepository
import javax.inject.Inject

class BattleRepositoryImpl @Inject constructor(
    private val dataSource: BattleDateSource
) : BattleRepository{
    override suspend fun getWaitingBattleInfo(userId: Long, limit: Int, page: Int) : WaitingBattleResponse =
        dataSource.getWaitingBattleInfo(userId, limit, page)

    override suspend fun getThreeKeywords(userId: Long): KeywordsResponse =
        dataSource.getThreeKeywords(userId)

    override suspend fun getOpponentsList(userId: Long): OpponentsResponse =
        dataSource.getOpponentsList(userId)

    override suspend fun getMyBattleProgressInfo(): List<MyBattleProgressDto> =
        dataSource.getMyBattleProgressInfo()

    override suspend fun getMyBattleCompletionInfo(): List<MyBattleCompletionDto> =
        dataSource.getMyBattleCompletion()

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