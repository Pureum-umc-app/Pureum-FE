package ko.co.data.source.profile

import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.ProfileInfoResponse
import kr.co.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
): ProfileRepository {
    override suspend fun getProfileInfo(userId: Long): ProfileInfoResponse =
        dataSource.getProfileInfo(userId)

    override suspend fun withdrawal(userId: Long): DefaultResponse =
        dataSource.withdrawal(userId)
}