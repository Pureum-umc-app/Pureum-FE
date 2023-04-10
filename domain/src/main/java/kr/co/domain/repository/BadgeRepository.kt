package kr.co.domain.repository

import kr.co.domain.model.BadgeInfo
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.SaveBadgeRequest

interface BadgeRepository {
    suspend fun saveBadge(saveBadgeReq: SaveBadgeRequest, userId: Long): DefaultResponse
    suspend fun getBadges(userId: Long): BadgeInfo
}