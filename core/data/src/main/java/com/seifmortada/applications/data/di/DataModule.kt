package com.seifmortada.applications.data.di

import com.seifmortada.applications.data.repository.RemoteCharactersRepository
import com.seifmortada.applications.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(ktorClient: HttpClient): CharactersRepository {
        return RemoteCharactersRepository(ktorClient)
    }
}