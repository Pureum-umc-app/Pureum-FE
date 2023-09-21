package kr.co.domain.repository

import kr.co.domain.model.*

interface QuestRepository {
    suspend fun todayKeyword(userId: Long): TodayKeywordResponse
    suspend fun sentencesIncomplete(userId: Long): SentencesIncompleteResponse
    suspend fun sentencesComplete(userId: Long): SentenceCompleteResponse
    suspend fun sentencesList(userId: Long, word_id: Long, page: Int, limit: Int,  sort: String): SentencesListResponse
    suspend fun writeSentences(keywordId: Long, sentence: String, status: String, userId: Long): WriteSentencesResponse
    suspend fun getProfileInfo(userId: Long) : ProfileInfoResponse
    suspend fun blameSentence(sentenceId : Long) : BlameSentenceResponse
    suspend fun sentenceLike(sentenceId: Long, userId: Long): SentenceLikeResponse
}