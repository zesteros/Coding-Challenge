package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.data.api.PageService
import com.angelo.codingchallenge.domain.repository.PageRemoteDataSource
import com.angelo.codingchallenge.domain.repository.PageRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun providePageRemoteDataSource(pageService: PageService) : PageRemoteDataSource = PageRemoteDataSourceImpl(pageService)
}