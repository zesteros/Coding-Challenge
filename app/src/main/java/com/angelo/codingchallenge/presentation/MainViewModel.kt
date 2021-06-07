package com.angelo.codingchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.angelo.codingchallenge.domain.usecase.GetPagesUseCase

class MainViewModel(private val pagesUseCase: GetPagesUseCase): ViewModel() {
    fun getPage() = liveData {
        val page = pagesUseCase.execute()
        emit(page)
    }
}