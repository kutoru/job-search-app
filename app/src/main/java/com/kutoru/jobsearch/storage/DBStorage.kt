package com.kutoru.jobsearch.storage

import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy

interface DBStorage {
    suspend fun addVacancies(vacancies: List<Vacancy>): Result<Unit>
    suspend fun addOffers(offers: List<Offer>): Result<Unit>

    suspend fun getVacancies(limit: Int): Result<List<Vacancy>>
    suspend fun getOffers(): Result<List<Offer>>

    suspend fun setVacancyAsFavorite(id: String): Result<Unit>
    suspend fun unsetVacancyAsFavorite(id: String): Result<Unit>

    suspend fun clear(): Result<Unit>
}
