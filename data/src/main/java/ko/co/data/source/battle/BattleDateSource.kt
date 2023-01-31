package ko.co.data.source.battle

import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.WaitingBattleDto
import javax.inject.Inject

class BattleDateSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getWaitingBattleInfo() : List<WaitingBattleDto> {
        // TODO: 임시
        val battleList = MutableList(3) { battleIdx ->
            WaitingBattleDto(word = "구현", period = 10, message = "대결 수락 대기 중",
                opponentNickname = "%d 번째 상대".format(battleIdx + 1), opponentProfile = "")
        }
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return battleList
    }
}