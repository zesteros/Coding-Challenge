package com.angelo.codingchallenge.domain.repository

import com.angelo.codingchallenge.data.model.Card
import com.angelo.codingchallenge.data.model.Page

interface PageRepository {
    suspend fun getPage() : Page?
    suspend fun getPageFromAPI() : Page
    suspend fun getPageFromCache() : Page
}