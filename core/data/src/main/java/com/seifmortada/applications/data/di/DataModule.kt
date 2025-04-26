package com.seifmortada.applications.data.di

import com.seifmortada.applications.data.repository.RemoteCharactersRepository
import com.seifmortada.applications.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCharactersRepository(
        remoteCharactersRepository: RemoteCharactersRepository
    ): CharactersRepository
}

