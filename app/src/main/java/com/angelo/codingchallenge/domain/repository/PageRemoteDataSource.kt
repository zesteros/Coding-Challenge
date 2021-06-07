package com.angelo.codingchallenge.domain.repository

import com.angelo.codingchallenge.data.model.Sample
import retrofit2.Response

interface PageRemoteDataSource {
    suspend fun getPage() : Response<Sample>
}