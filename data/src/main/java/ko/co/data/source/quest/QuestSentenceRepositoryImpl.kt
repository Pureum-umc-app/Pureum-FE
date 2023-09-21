package ko.co.data.source.quest

import kr.co.domain.model.*
import kr.co.domain.repository.QuestRepository
import javax.inject.Inject

class QuestSentenceRepositoryImpl @Inject constructor(
    private val dataSource: QuestSentenceDataSource
) : QuestRepository {
    override suspend fun todayKeyword(userId: Long): TodayKeywordResponse {
        return dataSource.todayKeyword(userId)
    }

    override suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse {
        return dataSource.sentencesIncomplete(userId)
    }

    override suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse {
        return dataSource.sentencesComplete(userId)
    }

    override suspend fun sentencesList(userId: Long, word_id: Long, page: Int, limit: Int, sort: String): SentencesListResponse {
        return dataSource.sentencesList(userId, word_id, page, limit, sort)
    }

    override suspend fun writeSentences(keywordId: Long, sentence: String, status: String, userId: Long): WriteSentencesResponse {
        return dataSource.writeSentences(keywordId, sentence, status, userId)
    }

    override suspend fun getProfileInfo(userId: Long): ProfileInfoResponse =
        dataSource.getProfileInfo(userId)

    override suspend fun blameSentence(sentenceId: Long): BlameSentenceResponse =
        dataSource.blameSentence(sentenceId)
    override suspend fun sentenceLike(sentenceId: Long, userId: Long): SentenceLikeResponse =
        dataSource.sentenceLike(sentenceId, userId)
}