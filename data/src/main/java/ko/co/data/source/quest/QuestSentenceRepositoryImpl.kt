package ko.co.data.source.quest

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.repository.QuestRepository
import javax.inject.Inject

class QuestSentenceRepositoryImpl @Inject constructor(
    private val dataSource: QuestSentenceDataSource
) : QuestRepository {
    override suspend fun sentencesIncomplete(userId: Int): SentencesIncompleteResponse {
        return dataSource.sentencesIncomplete(userId)
    }

    override suspend fun sentencesComplete(userId: Int): SentenceCompleteResponse {
        return dataSource.sentencesComplete(userId)
    }
}