package ko.co.data.source.profile

import android.content.Context
import android.net.Uri
import kr.co.domain.model.ContactRequest
import kr.co.domain.model.ContactResponse
import kr.co.domain.model.DefaultResponse
import kr.co.domain.model.MySentenceInfoResponse
import kr.co.domain.model.MySentencesListResponse
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

    override suspend fun nicknameValidate(nickname: String): DefaultResponse =
        dataSource.nicknameValidate(nickname)

    override suspend fun editProfile(userId: Long, context: Context, imageUri: Uri?, nickname: String): DefaultResponse =
        dataSource.editProfile(userId, context, imageUri, nickname)


    override suspend fun getMySentenceList(): MySentencesListResponse =
        dataSource.getMySentenceList()

    override suspend fun deleteMySentence(sentenceId: Long): DefaultResponse =
        dataSource.deleteMySentence(sentenceId)

    override suspend fun modifyMySentence(sentence: String, sentenceId: Long): DefaultResponse  =
        dataSource.modifyMySentence(sentence, sentenceId)

    override suspend fun mySentenceInfo(sentenceId: Long): MySentenceInfoResponse =
        dataSource.mySentenceInfo(sentenceId)

    override suspend fun postContact(contactRequest: ContactRequest): ContactResponse =
        dataSource.postContact(contactRequest)

}