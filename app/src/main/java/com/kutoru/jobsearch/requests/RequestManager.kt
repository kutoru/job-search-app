package com.kutoru.jobsearch.requests

import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.VacancyResponse

interface RequestManager {
    suspend fun getOffers(): Result<List<Offer>>
    suspend fun getVacancies(limit: Int? = null): Result<VacancyResponse>
}
