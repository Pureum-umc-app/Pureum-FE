package kr.co.pureum.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ko.co.data.source.badge.BadgeDataSource
import ko.co.data.source.badge.BadgeRepositoryImpl
import ko.co.data.source.battle.BattleDateSource
import ko.co.data.source.battle.BattleRepositoryImpl
import ko.co.data.source.home.HomeDataSource
import ko.co.data.source.home.HomeRepositoryImpl
import ko.co.data.source.login.LoginDataSource
import ko.co.data.source.login.LoginRepositoryImpl
import ko.co.data.source.ranking.RankingDataSource
import ko.co.data.source.ranking.RankingRepositoryImpl
import kr.co.domain.repository.BadgeRepository
import kr.co.domain.repository.BattleRepository
import kr.co.domain.repository.HomeRepository
import kr.co.domain.repository.LoginRepository
import kr.co.domain.repository.RankingRepository
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
}