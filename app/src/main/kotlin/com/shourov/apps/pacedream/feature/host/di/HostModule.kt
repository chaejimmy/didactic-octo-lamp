package com.shourov.apps.pacedream.feature.host.di

import com.shourov.apps.pacedream.feature.host.data.HostApiService
import com.shourov.apps.pacedream.feature.host.data.HostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HostModule {
    
    @Provides
    @Singleton
    fun provideHostApiService(retrofit: Retrofit): HostApiService {
        return retrofit.create(HostApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideHostRepository(hostApiService: HostApiService): HostRepository {
        return HostRepository(hostApiService)
    }
}