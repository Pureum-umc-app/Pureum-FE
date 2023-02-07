package ko.co.data.source.badge

import kr.co.domain.repository.BadgeRepository
import javax.inject.Inject

class BadgeRepositoryImpl @Inject constructor(
    private val dataSource: BadgeDataSource
) : BadgeRepository {
    override suspend fun getBadgeInfo(): List<Int> =
        dataSource.getBadgeInfo()
}