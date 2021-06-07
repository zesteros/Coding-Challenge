package com.angelo.codingchallenge.data.repository

import android.util.Log
import com.angelo.codingchallenge.data.model.Page
import com.angelo.codingchallenge.domain.repository.PageCacheDataSource
import com.angelo.codingchallenge.domain.repository.PageRemoteDataSource
import com.angelo.codingchallenge.domain.repository.PageRepository

class PageRepositoryImpl(
    private val pageRemoteDataSource: PageRemoteDataSource,
    private val pageCacheDataSource: PageCacheDataSource
) : PageRepository {

    private val TAG: String =
        PageRepository::class.java.simpleName

    override suspend fun getPage(): Page? = getPageFromCache()

    override suspend fun getPageFromAPI(): Page {
        lateinit var page: Page
        try {
            val response = pageRemoteDataSource.getPage()
            val body = response.body()
            page = body?.page ?: getPageFromCache()
        } catch (exception: Exception) {
            Log.i(TAG, exception.message.toString())
        }
        return page
    }


    override suspend fun getPageFromCache(): Page {
        lateinit var page: Page
        try {
            var pageCache = pageCacheDataSource.getPageFromCache()
            if (pageCache != null && !pageCache.cards.isEmpty()) {
                page = pageCache
            } else {
                page = getPageFromAPI()
                pageCacheDataSource.savePageToCache(page)
            }
        } catch (exception: Exception) {
            Log.i(TAG, exception.message.toString())
        }
        return page
    }
}