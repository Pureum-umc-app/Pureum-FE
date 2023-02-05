package ko.co.data.source.quest

import kr.co.domain.model.SentencesDto
import kr.co.domain.repository.QuestRepository
import javax.inject.Inject

class QuestSentenceRepositoryImpl @Inject constructor(
    private val dataSource: QuestSentenceDataSource
) : QuestRepository {
    override suspend fun sentencesIncomplete(userId: Int): SentencesDto {
        return dataSource.sentencesIncomplete(1)
    }

    override suspend fun sentencesComplete(userId: Int): SentencesDto {
        return dataSource.sentencesIncomplete(1)
    }


}