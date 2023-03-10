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

    suspend fun sentencesList(limit: Int, page: Int, sort: String, userId: Long, word_id: Long): SentencesListResponse {
        var response = SentencesListResponse(
            code = 0, isSuccess = true, message = "요청에 성공하였습니다", result = List<SentencesListDto>(1) {
                SentencesListDto(
                    image = "기본", keyword = "한탄", keywordId = 1, likeNum = 0, nickname = "르미",
                    selfLike = false, sentence = "나는 한숨 쉬는 것을 한탄해", sentenceId = 1, time = "2022-02-09", userId = 1
                )
            }
        )
        withContext(Dispatchers.IO) {
            runCatching {
                pureumService.sentencesList(limit, page, sort, userId, word_id)
            }.onSuccess {
                response = it
            }.onFailure {
                Log.e(ContentValues.TAG, "sentences List Failed")
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
}