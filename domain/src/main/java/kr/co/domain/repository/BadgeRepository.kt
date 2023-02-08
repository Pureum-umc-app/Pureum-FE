package kr.co.domain.repository

interface BadgeRepository {
    suspend fun getBadgeInfo() : List<Int>
}