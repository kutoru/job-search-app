package com.kutoru.jobsearch

import com.kutoru.jobsearch.main.FavoriteUpdater
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy
import com.kutoru.jobsearch.requests.RequestManager
import com.kutoru.jobsearch.storage.DBStorage
import com.kutoru.jobsearch.storage.PersistentStorage
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    private val persistentStorage: PersistentStorage,
    private val apiManager: RequestManager,
    private val dbStorage: DBStorage,
    private val favoriteUpdater: FavoriteUpdater,
) {

    // if lastFetched was too long ago or isn't even present, refresh the data
    private suspend fun checkData(): Result<Unit> {
        if (
            persistentStorage.lastFetched != null &&
            persistentStorage.lastFetched!! < Calendar.getInstance().timeInMillis + 3_600_000
        ) {
            return Result.success(Unit)
        }

        dbStorage.clear()

        val apiResult = apiManager.getApiData()
        if (apiResult.isFailure) {
            return Result.failure(apiResult.exceptionOrNull()!!)
        }

        val offers = apiResult.getOrThrow().offers
        val vacancies = apiResult.getOrThrow().vacancies

        val offerResult = dbStorage.addOffers(offers)
        if (offerResult.isFailure) {
            return Result.failure(offerResult.exceptionOrNull()!!)
        }

        val vacancyResult = dbStorage.addVacancies(vacancies)
        if (vacancyResult.isFailure) {
            dbStorage.clear()
            return Result.failure(vacancyResult.exceptionOrNull()!!)
        }

        vacancies
            .filter { it.isFavorite }
            .forEach { favoriteUpdater.addVacancy(it.id) }

        persistentStorage.lastFetched = Calendar.getInstance().timeInMillis

        return Result.success(Unit)
    }

    suspend fun getOffers(): Result<List<Offer>> {
        val checkResult = checkData()
        if (checkResult.isFailure) {
            return Result.failure(checkResult.exceptionOrNull()!!)
        }

        return dbStorage.getOffers()
    }

    suspend fun getVacancies(limit: Int): Result<List<Vacancy>> {
        val checkResult = checkData()
        if (checkResult.isFailure) {
            return Result.failure(checkResult.exceptionOrNull()!!)
        }

        return dbStorage.getVacancies(limit)
    }

    suspend fun setVacancyAsFavorite(id: String): Result<Unit> {
        val result = dbStorage.setVacancyAsFavorite(id)
        if (result.isSuccess) {
            favoriteUpdater.addVacancy(id)
        }

        return result
    }

    suspend fun unsetVacancyAsFavorite(id: String): Result<Unit> {
        val result = dbStorage.unsetVacancyAsFavorite(id)
        if (result.isSuccess) {
            favoriteUpdater.removeVacancy(id)
        }

        return result
    }
}
