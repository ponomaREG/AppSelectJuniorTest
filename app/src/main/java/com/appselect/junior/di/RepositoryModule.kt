package com.appselect.junior.di

import com.appselect.junior.network.Client
import com.appselect.junior.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(client: Client): MainRepository{
        return MainRepository(client)
    }
}