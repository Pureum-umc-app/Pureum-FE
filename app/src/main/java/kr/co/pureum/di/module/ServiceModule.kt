package kr.co.pureum.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ko.co.data.remote.PureumLoginService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providePureumLoginService(@NetworkModule.PureumLoginRetrofit retrofit: Retrofit) : PureumLoginService =
        retrofit.create(PureumLoginService::class.java)
}