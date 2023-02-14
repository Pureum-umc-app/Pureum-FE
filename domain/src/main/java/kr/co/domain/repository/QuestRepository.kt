package kr.co.domain.repository

import kr.co.domain.model.SentenceCompleteResponse
import kr.co.domain.model.SentencesIncompleteResponse
import kr.co.domain.model.SentencesListResponse
import kr.co.domain.model.WriteSentencesResponse

interface QuestRepository {
    suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse
    suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse
    fun sentencesList(limit: Int, page: Int, sort: String, userId: Long, word_id: Long): SentencesListResponse
    suspend fun writeSentences(keywordId: Long, sentence: String, status: String, userId: Long): WriteSentencesResponse
}