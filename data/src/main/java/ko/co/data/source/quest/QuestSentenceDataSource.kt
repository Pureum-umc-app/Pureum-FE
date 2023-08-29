package ko.co.data.source.quest

import android.content.ContentValues
import android.util.Log
import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.*
import javax.inject.Inject

class QuestSentenceDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun todayKeyword(userId: Long): TodayKeywordResponse {
        var response = TodayKeywordResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다", result = List<SentencesDto>(3) {
                SentencesDto(
                    date = "2022-02-08", keyword = "한탄", keywordId = 1,
                    meaning = "원통하거나 뉘우치는 일이 있을 때 한숨을 쉬며 탄식함. 또는 그 한숨.", userId = 1)
            }
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.todayKeyword(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "Today Keyword Failed")
            }
        }
        return response
    }

    suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse {
        var sentencesIncompleteResponse = SentencesIncompleteResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다", result = List<SentencesDto>(3) {
                SentencesDto(
                    date = "2022-02-08", keyword = "한탄", keywordId = 1,
                    meaning = "원통하거나 뉘우치는 일이 있을 때 한숨을 쉬며 탄식함. 또는 그 한숨.", userId = 1
                )
            }
        )
        withContext(Dispatchers.IO){
            Thread.sleep(1000)

            runCatching {
                pureumService.sentencesIncomplete(userId)
            }.onSuccess {
                sentencesIncompleteResponse = it
            }.onFailure {
                Log.e(ContentValues.TAG, "Sentences Incomplete Failed")
            }

        }
        return sentencesIncompleteResponse
    }
    suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse {
        var response = SentenceCompleteResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다", result = List<SentencesDto>(3) {
                SentencesDto(
                    date = "2022-02-08", keyword = "구현", keywordId = 1,
                    meaning = "원통하거나 뉘우치는 일이 있을 때 한숨을 쉬며 탄식함. 또는 그 한숨.", userId = 1
                )
            }
        )
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            runCatching {
                pureumService.sentencesComplete(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "Sentences Complete Failed")
            }
        }
        return response
    }

    suspend fun sentencesList(userId: Long, wordId: Long, page: Int, limit: Int, sort: String): SentencesListResponse {
        var response = SentencesListResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다", result = List<SentencesListDto>(1) {
                SentencesListDto(
                    date = "2022-02-09", isBlamed = "F", isLiked = "T",  keyword = "한탄", keywordId = 1, likeCnt = 0,
                    nickname = "르미", profileImg = "기본", sentence = "나는 한숨 쉬는 것을 한탄해", sentenceId = 1,  userId = 1
                )
            }
        )
        withContext(Dispatchers.IO) {
            runCatching {
                Log.e(ContentValues.TAG, "$userId, $wordId, $page, $limit, $sort")
                pureumService.sentencesList(userId, wordId, page, limit, sort)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "sentences List Failed : $it")
            }
        }
        return response
    }
    suspend fun writeSentences(keywordId: Long, sentence: String, status: String, userId: Long) : WriteSentencesResponse {
        var response = WriteSentencesResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다", result = WriteSentencesDto(1)
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.sentencesWrite(WriteSentencesReq(keywordId, sentence, status, userId))
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "Sentences Write Failed")
            }
        }
        return response
    }

    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse {
        var response = ProfileInfoResponse(0, false, "getProfileInfo Failed",
            ProfileInfo(0, "nickname error", "profileUrl error")
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.getProfileInfo(userId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "getProfileInfo Failed: $it")
            }
        }
        return response
    }
    suspend fun blameSentence(sentenceId: Long) : BlameSentenceResponse {
        var response = BlameSentenceResponse(code = 0, isSuccess = false, message = "blameSentence Failed", result = "")
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.blameSentence(sentenceId)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "blameSentence Failed: $it")
            }
        }
        return response
    }
}