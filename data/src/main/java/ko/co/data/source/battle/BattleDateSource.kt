package ko.co.data.source.battle

import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleCompletionDto
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgMoreDto
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.AllBattleProgressDto
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompMoreDto
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
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
        val keywords = mutableListOf("복구", "신년", "단련")
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
            MyBattleCompletionDto(keyword = "구현", winnerProfile="", winnerNickname = "푸름", type = 0)
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

    suspend fun getMyBattleProgMoreInfo() : MyBattleProgMoreDto {
        val progressMore = MyBattleProgMoreDto(keyword = "구현", nickname = "푸름", mySentence = "황폐화된 자연을 복구하였다.",
            opponentNickname = "르미", day = 10, opponentSentence = "떨어진 내 성적을 복구하였다.", mySentenceLikeNum = 5, opSentenceLikeNum = 3,
            mySentenceLike = true, opSentenceLike = false)
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return progressMore
    }

    suspend fun getMyBattleCompMoreInfo() : MyBattleCompMore {
        val compMore = MyBattleCompMore( code = 1000, isSuccess = true, message = "요청에 성공했습니다.", result = MyBattleCompMoreDto(battleId = 1,
            duration = 10, loserId = 0, loserImage = "", loserLikeCnt = 3, loserNickname = "르미", loserSentence= "떨어진 내 성적을 복구하였다.",
        loserSentenceId = 1,
        oppLike = 0,
        situation = 0,
        userLike= 0,
        winnerId= 0,
        winnerImage= "",
        winnerLikeCnt= 10,
        winnerNickname= "푸름",
        winnerSentence = "황폐화된 자연을 복구하였다.",
        winnerSentenceId = 10,
        winnerUserId = 2)
        )

            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
            }
        return compMore
    }

    suspend fun getAllBattleProgressInfo() : AllBattleProgress {
        val allBattleProg = AllBattleProgress( code = 1000, isSuccess = true, message = "요청에 성공했습니다.",
            result = List(8) { AllBattleProgressDto(
                battleId = 1,
                challengedId = 1,
                challengedLikeCnt = 4,
                challengedNickname = "소다",
                challengedProfileImg = "",
                challengerId = 2,
                challengerLikeCnt = 7,
                challengerNickname = "보리",
                challengerProfileImg = "",
                isChallengedLike = 0,
                isChallengerLike = 1,
                keyword = "낭만",
                keywordId = 4,
                duration = "D-5"
            )
        })
            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
            }
        return allBattleProg
    }

    suspend fun getAllBattleCompletionInfo() : AllBattleCompletion {
        val allBattleComp = AllBattleCompletion( code = 1000, isSuccess = true, message = "요청에 성공했습니다.",
            result = List(8) {
                AllBattleCompletionDto(
                    battleId = 4,
                    otherProfileImg = "",
                    type = 0,
                    winnerId = 1,
                    winnerNickname = "푸름",
                    winnerProfileImg = "",
                    word = "마힘",
                    wordId = 4
                )
            })
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return allBattleComp
    }

    suspend fun getAllBattleProgMoreInfo() : AllBattleProgMore {
        val allBattleProgMore = AllBattleProgMore( code = 1000, isSuccess = true, message = "요청에 성공했습니다.", result = AllBattleProgMoreDto( battleId = 1,
            challengedId = 1, challengedImage = "", challengedLikeCnt = 3, challengedNickname = "푸름", challengedSentence = "황폐화된 자연을 복구하였다.",
            challengedSentenceId = 2, challengerId = 3, challengerImage = "", challengerLikeCnt = 4, challengerNickname = "르미",
            challengerSentence = "떨어진 내 성적을 복구하였다.", challengerSentenceId = 4, duration = 10, keyword = "복구", keywordId = 1,
            oppLike = 0, remainDuration = "D-10", selfLike = 1, status = "A")
        )
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return allBattleProgMore
    }


}