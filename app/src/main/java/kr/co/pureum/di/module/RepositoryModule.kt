package kr.co.pureum.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ko.co.data.source.attendances.AttendancesDataSource
import ko.co.data.source.attendances.AttendancesRepositoryImpl
import ko.co.data.source.badge.BadgeDataSource
import ko.co.data.source.badge.BadgeRepositoryImpl
import ko.co.data.source.battle.BattleDateSource
import ko.co.data.source.battle.BattleRepositoryImpl
import ko.co.data.source.home.HomeDataSource
import ko.co.data.source.home.HomeRepositoryImpl
import ko.co.data.source.login.LoginDataSource
import ko.co.data.source.login.LoginRepositoryImpl
import ko.co.data.source.profile.ProfileDataSource
import ko.co.data.source.profile.ProfileRepositoryImpl
import ko.co.data.source.quest.QuestSentenceDataSource
import ko.co.data.source.quest.QuestSentenceRepositoryImpl
import ko.co.data.source.ranking.RankingDataSource
import ko.co.data.source.ranking.RankingRepositoryImpl
import kr.co.domain.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesLoginRepository(loginDataSource: LoginDataSource) : LoginRepository =
        LoginRepositoryImpl(loginDataSource)

    @Provides
    @Singleton
    fun providesHomeRepository(homeDataSource: HomeDataSource) : HomeRepository =
        HomeRepositoryImpl(homeDataSource)

    @Provides
    @Singleton
    fun providesRankingRepository(rankingDataSource: RankingDataSource) : RankingRepository =
        RankingRepositoryImpl(rankingDataSource)

    @Provides
    @Singleton
    fun providesBattleRepository(battleDateSource: BattleDateSource) : BattleRepository =
        BattleRepositoryImpl(battleDateSource)

    @Provides
    @Singleton
    fun providesBadgeRepository(badgeDataSource: BadgeDataSource) : BadgeRepository =
        BadgeRepositoryImpl(badgeDataSource)

    @Provides
    @Singleton
    fun providesQuestRepository(questSentenceDataSource: QuestSentenceDataSource) : QuestRepository =
        QuestSentenceRepositoryImpl(questSentenceDataSource)

    @Provides
    @Singleton
    fun providesProfileRepository(profileDataSource: ProfileDataSource) : ProfileRepository =
        ProfileRepositoryImpl(profileDataSource)

    @Provides
    @Singleton
    fun providesAttendancesRepository(attendancesDataSource: AttendancesDataSource) : AttendancesRepository =
        AttendancesRepositoryImpl(attendancesDataSource)
}