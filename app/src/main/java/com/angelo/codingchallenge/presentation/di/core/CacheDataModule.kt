package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.domain.repository.PageCacheDataSource
import com.angelo.codingchallenge.domain.repository.PageCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideCacheDataModule() : PageCacheDataSource = PageCacheDataSourceImpl()
}