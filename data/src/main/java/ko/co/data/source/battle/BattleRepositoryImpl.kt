package ko.co.data.source.battle

import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.BattleControlResponse
import kr.co.domain.model.BattleId
import kr.co.domain.model.BattleLike
import kr.co.domain.model.BattleLikeReq
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.BattleSentenceRequest
import kr.co.domain.model.BattleSentenceResponse
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompletion
import kr.co.domain.model.MyBattleProgMore
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

    override suspend fun acceptBattle(battleId: BattleId): BattleControlResponse =
        dataSource.acceptBattle(battleId)

    override suspend fun refuseBattle(battleId: BattleId): BattleControlResponse =
        dataSource.refuseBattle(battleId)

    override suspend fun cancelBattle(battleId: BattleId): BattleControlResponse =
        dataSource.cancelBattle(battleId)

    override suspend fun getThreeKeywords(userId: Long): KeywordsResponse =
        dataSource.getThreeKeywords(userId)

    override suspend fun getOpponentsList(userId: Long): OpponentsResponse =
        dataSource.getOpponentsList(userId)

    override suspend fun getMyProfileImage(userId: Long): ProfileImageResponse =
        dataSource.getMyProfileImage(userId)

    override suspend fun sendBattleRequest(userId: Long, opponentId: Long, wordId: Long, sentence: String, duration: Int): BattleRequestResponse =
        dataSource.sendBattleRequest(userId, opponentId, wordId, sentence, duration)

    override suspend fun writeSentence(battleId: Long, sentence: String): BattleSentenceResponse =
        dataSource.writeSentence(battleId, sentence)

    override suspend fun getMyBattleProgressInfo(userId: Long): MyBattleProgress =
        dataSource.getMyBattleProgressInfo(userId)

    override suspend fun getMyBattleCompletionInfo(userId: Long): MyBattleCompletion =
        dataSource.getMyBattleCompletion(userId)

    override suspend fun getMyBattleProgMoreInfo(itemIdx: Long): MyBattleProgMore =
        dataSource.getMyBattleProgMoreInfo(itemIdx)

    override suspend fun getMyBattleCompMoreInfo(itemIdx: Long): MyBattleCompMore =
        dataSource.getMyBattleCompMoreInfo(itemIdx)

    override suspend fun getAllBattleProgressInfo(): AllBattleProgress =
        dataSource.getAllBattleProgressInfo()

    override suspend fun getAllBattleCompletionInfo(): AllBattleCompletion =
        dataSource.getAllBattleCompletionInfo()

    override suspend fun getAllBattleProgMoreInfo(itemIdx: Long): AllBattleProgMore =
        dataSource.getAllBattleProgMoreInfo(itemIdx)

    override suspend fun getAllBattleCompMoreInfo(itemIdx: Long): AllBattleCompMore =
        dataSource.getAllBattleCompMoreInfo(itemIdx)

    override suspend fun postBattleLike(sentenceId: Long, userId: Long): BattleLike =
        dataSource.postBattleLike(sentenceId, userId)
}