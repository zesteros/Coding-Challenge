package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.data.repository.PageRepositoryImpl
import com.angelo.codingchallenge.domain.repository.PageCacheDataSource
import com.angelo.codingchallenge.domain.repository.PageRemoteDataSource
import com.angelo.codingchallenge.domain.repository.PageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        pageRemoteDataSource: PageRemoteDataSource,
        pageCacheDataSource: PageCacheDataSource
    ): PageRepository = PageRepositoryImpl(pageRemoteDataSource, pageCacheDataSource)
}