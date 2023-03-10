package ko.co.data.source.home

import android.os.Build
import androidx.annotation.RequiresApi
import kr.co.domain.model.DailyRecord
import kr.co.domain.model.DailyRecordResponse
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.HomeResponse
import kr.co.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository{
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getHomeInfo(userId: Long): HomeResponse =
        dataSource.getHomeInfo(userId)

    override suspend fun setPurposeTime(userId: Long, purposeTime: Int): DefaultResponse =
        dataSource.setPurposeTime(userId, purposeTime)

    override suspend fun commitDailyRecord(userId: Long, postUseTimeAndCountReq: DailyRecord): DailyRecordResponse =
        dataSource.commitDailyRecord(userId, postUseTimeAndCountReq)
}