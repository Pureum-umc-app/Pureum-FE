package ko.co.data.source.badge

import ko.co.data.remote.PureumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BadgeDataSource @Inject constructor(
    private val pureumService: PureumService
) {
    suspend fun getBadgeInfo() : List<Int> {
        // TODO: 임시
        val badgeList = listOf(1, -1, -1, 0, 0, 0, 0, -1, -1, 0)
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return badgeList
    }
}