package com.kutoru.jobsearch.requests

import com.kutoru.jobsearch.models.ApiData
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAppApi {
    @GET("/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getApiData(): Response<ApiData>
}
