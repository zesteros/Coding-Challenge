package com.angelo.codingchallenge.data.api

import com.angelo.codingchallenge.data.model.Sample
import retrofit2.Response
import retrofit2.http.GET
interface PageService {
    @GET("test/home")
    suspend fun getPage() : Response<Sample>
}