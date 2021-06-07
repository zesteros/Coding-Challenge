package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.domain.repository.PageRepository
import com.angelo.codingchallenge.domain.usecase.GetPagesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPageUseCase(pageRepository: PageRepository) : GetPagesUseCase = GetPagesUseCase(pageRepository)
}