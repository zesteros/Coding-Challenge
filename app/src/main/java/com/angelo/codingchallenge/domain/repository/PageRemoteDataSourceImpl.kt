package com.angelo.codingchallenge.domain.repository

import com.angelo.codingchallenge.data.api.PageService
import com.angelo.codingchallenge.data.model.Page
import com.angelo.codingchallenge.data.model.Sample
import retrofit2.Response

class PageRemoteDataSourceImpl(private val pageService: PageService) : PageRemoteDataSource {
    override suspend fun getPage(): Response<Sample> = pageService.getPage()
}