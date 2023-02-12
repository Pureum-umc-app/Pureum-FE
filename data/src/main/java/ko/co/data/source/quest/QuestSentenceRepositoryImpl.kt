package ko.co.data.source.quest

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SentencesListResponse
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

    override fun sentencesList(limit: Int, page: Int, sort: String, userId: Long, word_id: Int): SentencesListResponse {
        return dataSource.sentencesList(limit, page, sort, userId, word_id)
    }
}