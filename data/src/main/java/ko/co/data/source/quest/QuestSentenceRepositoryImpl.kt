package ko.co.data.source.quest

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SentencesListResponse
import kr.co.domain.model.WriteSentencesResponse
import kr.co.domain.repository.QuestRepository
import javax.inject.Inject

class QuestSentenceRepositoryImpl @Inject constructor(
    private val dataSource: QuestSentenceDataSource
) : QuestRepository {
    override suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse {
        return dataSource.sentencesIncomplete(userId)
    }

    override suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse {
        return dataSource.sentencesComplete(userId)
    }

    override fun sentencesList(limit: Int, page: Int, sort: String, userId: Long, word_id: Long): SentencesListResponse {
        return dataSource.sentencesList(limit, page, sort, userId, word_id)
    }

    override suspend fun writeSentences(keywordId: Long, sentence: String, status: String, userId: Long): WriteSentencesResponse {
        return dataSource.writeSentences(keywordId, sentence, status, userId)
    }
}