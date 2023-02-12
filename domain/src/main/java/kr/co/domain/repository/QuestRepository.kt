package kr.co.domain.repository

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SentencesListResponse

interface QuestRepository {
    suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse
    suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse
    fun sentencesList(limit: Int, page: Int, sort: String, userId: Long, word_id: Int): SentencesListResponse
}