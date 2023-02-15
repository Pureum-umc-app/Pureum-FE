package ko.co.data.source.attendances

import kr.co.domain.model.AttendancesCheckResponse
import kr.co.domain.model.StampListResponse
import kr.co.domain.model.UserId
import kr.co.domain.repository.AttendancesRepository
import javax.inject.Inject

class AttendancesRepositoryImpl @Inject constructor(
    private val dataSource: AttendancesDataSource
) : AttendancesRepository {
    override suspend fun getStampList(userId: Long): StampListResponse =
        dataSource.getStampList(userId)
    override suspend fun attendancesCheck(userId: UserId): AttendancesCheckResponse =
        dataSource.attendanceCheck(userId)
}