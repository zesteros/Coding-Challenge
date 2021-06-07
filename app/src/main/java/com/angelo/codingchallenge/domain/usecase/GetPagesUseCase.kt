package com.angelo.codingchallenge.domain.usecase

import com.angelo.codingchallenge.data.model.Page
import com.angelo.codingchallenge.domain.repository.PageRepository

class GetPagesUseCase(private val pageRepository: PageRepository) {
    suspend fun execute() : Page? = pageRepository.getPage()
}