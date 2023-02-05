package kr.co.domain.repository

import kr.co.domain.model.SentencesDto

interface QuestRepository {
    suspend fun sentencesIncomplete(userId: Int): SentencesDto
    suspend fun sentencesComplete(userId: Int): SentencesDto
}