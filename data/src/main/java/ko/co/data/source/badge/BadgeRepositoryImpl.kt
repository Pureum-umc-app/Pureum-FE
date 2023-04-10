package ko.co.data.source.badge

import kr.co.domain.model.BadgeInfo
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.SaveBadgeRequest
import kr.co.domain.repository.BadgeRepository
import javax.inject.Inject

class BadgeRepositoryImpl @Inject constructor(
    private val dataSource: BadgeDataSource
) : BadgeRepository {
    override suspend fun saveBadge(saveBadgeReq: SaveBadgeRequest, userId: Long): DefaultResponse =
        dataSource.saveBadge(saveBadgeReq, userId)
    override suspend fun getBadges(userId: Long): BadgeInfo =
        dataSource.getBadges(userId)
}