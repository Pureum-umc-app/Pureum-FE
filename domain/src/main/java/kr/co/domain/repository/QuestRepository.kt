package kr.co.domain.repository

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse

interface QuestRepository {
    suspend fun sentencesIncomplete(userId: Int): SentencesIncompleteResponse
    suspend fun sentencesComplete(userId: Int): SentenceCompleteResponse
}