package com.kutoru.jobsearch.requests

import com.kutoru.jobsearch.models.ApiData

interface RequestManager {
    suspend fun getApiData(): Result<ApiData>
}
