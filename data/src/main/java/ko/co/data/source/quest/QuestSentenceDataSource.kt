package ko.co.data.source.quest

import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.domain.model.SentencesDto
import javax.inject.Inject

class QuestSentenceDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun sentencesIncomplete(userId: Int): SentencesDto {
        withContext(Dispatchers.IO){
            Thread.sleep(1000)
        }
        return sentencesIncomplete(1)
    }

    private suspend fun sentencesComplete(userId: Int): SentencesDto {
        withContext(Dispatchers.IO){
            Thread.sleep(1000)
        }
        return sentencesComplete(1)
    }
}