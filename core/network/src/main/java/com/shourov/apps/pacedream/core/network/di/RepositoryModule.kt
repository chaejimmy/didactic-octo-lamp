package com.shourov.apps.pacedream.core.network.di

import com.shourov.apps.pacedream.core.network.PaceDreamNetworkRepository
import com.shourov.apps.pacedream.core.network.PaceDreamNetworkRepositoryImpl
import com.shourov.apps.pacedream.core.network.repository.UserRepository
import com.shourov.apps.pacedream.core.network.repository.UserRepositoryImpl
import com.shourov.apps.pacedream.core.network.services.PaceDreamApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindPaceDreamApiServiceRepository(
        impl: PaceDreamNetworkRepositoryImpl,
    ): PaceDreamNetworkRepository

}

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule{
    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: PaceDreamApiService,
    ): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}