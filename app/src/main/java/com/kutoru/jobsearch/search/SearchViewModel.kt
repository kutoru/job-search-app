package com.kutoru.jobsearch.search

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.favorite_manager.FavoriteUpdater
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy
import com.kutoru.jobsearch.models.VacancyFavorite
import com.kutoru.jobsearch.requests.RequestManager
import com.kutoru.jobsearch.storage.StorageManager
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val favoriteUpdater: FavoriteUpdater,
    private val requestManager: RequestManager,
    private val storageManager: StorageManager,
) : ViewModel() {

    suspend fun setVacancyFavorite(id: String, isFavorite: Boolean): Result<Unit> {
        val result = storageManager.setVacFav(VacancyFavorite(id, isFavorite))
        if (result.isFailure) {
            return result
        }

        favoriteUpdater.updateVacancy(id, isFavorite)
        return result
    }

    suspend fun getOffers(): Result<List<Offer>> {
        return requestManager.getOffers()
    }

    suspend fun getVacancies(limit: Int? = null): Result<List<Vacancy>> {
        val vacancyResult = requestManager.getVacancies(limit)
        if (vacancyResult.isFailure) {
            return vacancyResult
        }

        val vacancies = vacancyResult.getOrThrow()

        val favoriteResult = storageManager.getAllVacFavs()
        if (favoriteResult.isFailure) {
            return vacancyResult
        }

        val favorites = favoriteResult.getOrThrow()

        vacancies.forEach { vacancy ->
            val favorite = favorites.find { vacancy.id == it.id }
            if (favorite != null) {
                vacancy.isFavorite = favorite.isFavorite
            }
        }

        return vacancyResult
    }
}
