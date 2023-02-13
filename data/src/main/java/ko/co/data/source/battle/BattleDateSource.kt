package ko.co.data.source.battle

import android.content.ContentValues.TAG
import android.util.Log
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.AllBattleCompMore
import kr.co.domain.model.AllBattleCompMoreDto
import kr.co.domain.model.AllBattleCompletion
import kr.co.domain.model.AllBattleCompletionDto
import kr.co.domain.model.AllBattleProgMore
import kr.co.domain.model.AllBattleProgMoreDto
import kr.co.domain.model.AllBattleProgress
import kr.co.domain.model.AllBattleProgressDto
import kr.co.domain.model.BattleRequest
import kr.co.domain.model.BattleRequestResponse
import kr.co.domain.model.Keyword
import kr.co.domain.model.KeywordsResponse
import kr.co.domain.model.MyBattleCompMore
import kr.co.domain.model.MyBattleCompMoreDto
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.domain.model.MyBattleProgressDto
import kr.co.domain.model.OpponentsResponse
import kr.co.domain.model.ProfileImage
import kr.co.domain.model.ProfileImageResponse
import kr.co.domain.model.WaitingBattleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class BattleDateSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getWaitingBattleInfo(userId: Long, limit: Int, page: Int) : WaitingBattleResponse {
        var battleList = WaitingBattleResponse(0, false, "", listOf())
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getWaitingBattleInfo(userId, limit, page)
            }.onSuccess {
                battleList = it
            }.onFailure {
                Log.e(TAG, "getWaitingBattleInfo failed: $it")
            }
        }
        return battleList
    }

    suspend fun getThreeKeywords(userId: Long): KeywordsResponse {
        var response = KeywordsResponse(0, false, "", listOf())
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getThreeKeywords(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getThreeKeywords failed: $it")
            }
        }
        return response
    }

    suspend fun getOpponentsList(userId: Long): OpponentsResponse {
        var response = OpponentsResponse(0, false, "", listOf())
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getOpponentsList(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getOpponentsList failed: $it")
            }
        }
        return response
    }

    suspend fun getMyProfileImage(userId: Long): ProfileImageResponse {
        var response = ProfileImageResponse(0, false, "", ProfileImage("", 0))
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getMyProfileImage(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "getMyProfileImage failed: $it")
            }
        }
        return response
    }

    suspend fun sendBattleRequest(userId: Long, opponentId: Long, wordId: Long, sentence: String, duration: Int): BattleRequestResponse {
        val battleRequest = BattleRequest(opponentId, userId, duration, sentence, wordId)
        var response = BattleRequestResponse(0, false, "", 0)
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.sendBattleRequest(battleRequest)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(TAG, "sendBattleRequest failed: $it")
            }
        }
        return response
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

    suspend fun getAllBattleCompMoreInfo() : AllBattleCompMore {
        val compMore = AllBattleCompMore( code = 1000, isSuccess = true, message = "요청에 성공했습니다.", result = AllBattleCompMoreDto(battleId = 1,
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
}