package kr.co.pureum.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ko.co.data.remote.PureumLoginService
import ko.co.data.remote.PureumService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providePureumLoginService(@NetworkModule.PureumLoginRetrofit retrofit: Retrofit) : PureumLoginService =
        retrofit.create(PureumLoginService::class.java)

    @Provides
    @Singleton
    fun providePureumService(@NetworkModule.PureumRetrofit retrofit: Retrofit) : PureumService =
        retrofit.create(PureumService::class.java)
}