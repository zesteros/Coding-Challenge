package com.angelo.codingchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angelo.codingchallenge.domain.usecase.GetPagesUseCase

class MainViewModelFactory(private val pagesUseCase: GetPagesUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(pagesUseCase) as T
    }
}