package kr.co.pureum.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ko.co.data.source.login.LoginDataSource
import ko.co.data.source.login.LoginRepositoryImpl
import kr.co.domain.repository.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesLoginRepository(loginDataSource: LoginDataSource) : LoginRepository =
        LoginRepositoryImpl(loginDataSource)
}