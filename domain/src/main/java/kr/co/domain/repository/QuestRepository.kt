package kr.co.domain.repository

import kr.co.domain.model.*

interface QuestRepository {
    suspend fun todayKeyword(userId: Long): TodayKeywordResponse
    suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse
    suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse
    suspend fun sentencesList(limit: Int, page: Int, sort: String, userId: Long, word_id: Long): SentencesListResponse
    suspend fun writeSentences(keywordId: Long, sentence: String, status: String, userId: Long): WriteSentencesResponse
    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse
}