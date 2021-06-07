package com.angelo.codingchallenge.data.repository

import android.util.Log
import com.angelo.codingchallenge.data.model.*
import com.angelo.codingchallenge.domain.repository.PageCacheDataSource
import com.angelo.codingchallenge.domain.repository.PageRemoteDataSource
import com.angelo.codingchallenge.domain.repository.PageRepository
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response

@RunWith(PowerMockRunner::class)
@PrepareForTest(PageRepositoryImpl::class, Log::class)
class PageRepositoryImplTest {

    private val pageRemoteDataSource: PageRemoteDataSource = mock(PageRemoteDataSource::class.java)
    private val pageCacheDataSource: PageCacheDataSource = mock(PageCacheDataSource::class.java)
    private lateinit var pageRepository: PageRepository
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        mockStatic(Log::class.java)
        Dispatchers.setMain(testDispatcher)
        pageRepository = PageRepositoryImpl(pageRemoteDataSource, pageCacheDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPageFromAPI when page is not empty`() {
        val page = Page(
            listOf(
                Card(
                    CardX(
                        Attributes(Font(2), "#ffff"),
                        Description(AttributesX(FontX(23), "#ffff"), "description"),
                        Image(Size(23, 12), "https://google.com"),
                        Title(AttributesXX(FontXX(12), "#ffff"), "title"),
                        "Hello"
                    ), "text"
                )
            )
        )
        pageRemoteDataSource.stub {
            onBlocking {
                getPage()
            }.thenReturn(Response.success(Sample(page))).then {
                runBlockingTest {
                    pageRepository.getPageFromAPI()
                    verify(pageRemoteDataSource).getPage()
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPageFromAPI when page is empty`() {
        pageRemoteDataSource.stub {
            onBlocking {
                getPage()
            }.thenReturn(Response.error(404, ResponseBody.create(null, ""))).then {
                runBlockingTest {
                    pageRepository.getPageFromAPI()
                    verify(pageRemoteDataSource, never()).getPage()
                }
            }
        }
    }
}