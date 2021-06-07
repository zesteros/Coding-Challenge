package com.angelo.codingchallenge.domain.repository

import com.angelo.codingchallenge.data.model.Page

interface PageCacheDataSource {
    suspend fun getPageFromCache() : Page
    suspend fun savePageToCache(page : Page)
}