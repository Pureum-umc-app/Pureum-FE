package ko.co.data.source.battle

import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentDto
import kr.co.domain.model.WaitingBattleDto
import javax.inject.Inject

class BattleDateSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto> {
        // TODO: 임시
        val battleList = MutableList(3) {
            WaitingBattleDto(word = "구현", period = 10, message = "대결 수락 대기 중",
                opponentNickname = "%d 번째 상대".format(it + 1), opponentProfile = "")
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return battleList
    }

    suspend fun getThreeKeywords() : List<String> {
        // TODO: 임시
        val keywords = mutableListOf<String>("복구", "신년", "단련")
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return keywords
    }

    suspend fun getMyBattleProgressInfo() : List<MyBattleProgressDto> {
        val progressList = MutableList(8){
            MyBattleProgressDto(keyword = "구현", firstUserName = "소다", firstUserProfile = "", secondUserName = "물댕", secondUserProfile = "",
                day = "D-2", firstLike = "", firstLikeNum = 4, secondLike ="", secondLikeNum = 2)
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return progressList
    }

    suspend fun getMyBattleCompletion() : List<MyBattleCompletionDto>{
        val completionList = MutableList(8){
            MyBattleCompletionDto(keyword = "구현", winnerProfile="", winnerNickname = "푸름")
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return completionList
    }

    suspend fun getDefinition(keyword: String) : String {
        val definition = when(keyword) {
            "복구" -> "손실 이전의 상태로 회복함."
            "신년" -> "새로 시작되는 해."
            "단련" -> "어떤 일을 반복하여 익숙하게 됨. 또는 그렇게 함."
            else -> ""
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return definition
    }

    suspend fun getOpponentsList() : List<OpponentDto> {
        val opponentsList = MutableList(10){
            OpponentDto(nickname = "%d 번째 상대".format(it + 1), profile = "")
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return opponentsList
    }

    suspend fun getAdditionalOpponents(position: Int, itemCount: Int) : List<OpponentDto> {
        val opponentsList = MutableList(itemCount){
            OpponentDto(nickname = "%d 번째 상대".format(position + it + 1), profile = "")
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return opponentsList
    }
}