package ko.co.data.source.battle

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletion
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.OpponentsResponse
import kr.co.domain.model.ProfileImageResponse
import kr.co.domain.model.WaitingBattleResponse
import kr.co.domain.model.MyBattleProgress
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

    override suspend fun getMyProfileImage(userId: Long): ProfileImageResponse =
        dataSource.getMyProfileImage(userId)

    override suspend fun sendBattleRequest(userId: Long, opponentId: Long, wordId: Long, sentence: String, duration: Int): BattleRequestResponse =
        dataSource.sendBattleRequest(userId, opponentId, wordId, sentence, duration)

    override suspend fun getMyBattleProgressInfo(userId: Long): MyBattleProgress =
        dataSource.getMyBattleProgressInfo(userId)

    override suspend fun getMyBattleCompletionInfo(userId: Long): MyBattleCompletion =
        dataSource.getMyBattleCompletion(userId)

    override suspend fun getMyBattleProgMoreInfo(): MyBattleProgMoreDto =
        dataSource.getMyBattleProgMoreInfo()

    override suspend fun getMyBattleCompMoreInfo(): MyBattleCompMore =
        dataSource.getMyBattleCompMoreInfo()

    override suspend fun getAllBattleProgressInfo(): AllBattleProgress =
        dataSource.getAllBattleProgressInfo()

    override suspend fun getAllBattleCompletionInfo(): AllBattleCompletion =
        dataSource.getAllBattleCompletionInfo()

    override suspend fun getAllBattleProgMoreInfo(itemIdx: Long): AllBattleProgMore =
        dataSource.getAllBattleProgMoreInfo(itemIdx)

    override suspend fun getAllBattleCompMoreInfo(): AllBattleCompMore =
        dataSource.getAllBattleCompMoreInfo()
}