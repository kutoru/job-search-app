package com.kutoru.jobsearch.requests

import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy

interface RequestManager {
    suspend fun getOffers(): Result<List<Offer>>
    suspend fun getVacancies(): Result<List<Vacancy>>
}
