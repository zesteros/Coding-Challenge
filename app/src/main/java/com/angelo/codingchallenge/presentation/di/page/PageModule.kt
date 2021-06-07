package com.angelo.codingchallenge.presentation.di.page

import com.angelo.codingchallenge.domain.usecase.GetPagesUseCase
import com.angelo.codingchallenge.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PageModule {
    @PageScope
    @Provides
    fun providePageViewModelFactory(pagesUseCase: GetPagesUseCase): MainViewModelFactory =
        MainViewModelFactory(pagesUseCase)
}