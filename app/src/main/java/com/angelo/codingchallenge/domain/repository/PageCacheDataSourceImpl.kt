package com.angelo.codingchallenge.domain.repository

import com.angelo.codingchallenge.data.model.Card
import com.angelo.codingchallenge.data.model.Page

class PageCacheDataSourceImpl : PageCacheDataSource {
    private var page = Page(ArrayList())
    override suspend fun getPageFromCache(): Page = page
    override suspend fun savePageToCache(page: Page) {
        this.page = page
    }
}