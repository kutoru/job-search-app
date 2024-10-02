package com.kutoru.jobsearch

import com.kutoru.jobsearch.favorite_manager.FavoriteUpdater
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.VacancyFavorite
import com.kutoru.jobsearch.models.VacancyResponse
import com.kutoru.jobsearch.requests.RequestManager
import com.kutoru.jobsearch.storage.StorageManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    private val favoriteUpdater: FavoriteUpdater,
    private val requestManager: RequestManager,
    private val storageManager: StorageManager,
) {

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

    suspend fun getVacancies(limit: Int? = null, onlyFavorite: Boolean = false): Result<VacancyResponse> {
        val vacancyResult = requestManager.getVacancies(limit)
        if (vacancyResult.isFailure) {
            return vacancyResult
        }

        val response = vacancyResult.getOrThrow()

        val favoriteResult = storageManager.getAllVacFavs()
        if (favoriteResult.isFailure) {
            return vacancyResult
        }

        val favorites = favoriteResult.getOrThrow()

        response.vacancies.forEach { vacancy ->
            val favorite = favorites.find { vacancy.id == it.id }
            if (favorite != null) {
                vacancy.isFavorite = favorite.isFavorite
            }

            favoriteUpdater.updateVacancy(vacancy.id, vacancy.isFavorite)
        }

        if (onlyFavorite) {
            response.vacancies = response.vacancies.filter { it.isFavorite }
            return Result.success(response)
        }

        return vacancyResult
    }
}
