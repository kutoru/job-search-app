package com.kutoru.jobsearch.storage

import com.kutoru.jobsearch.models.VacancyFavorite

interface StorageManager {
    suspend fun getAllVacFavs(): Result<List<VacancyFavorite>>
    suspend fun setVacFav(vacancyFavorite: VacancyFavorite): Result<Unit>
    suspend fun getVacFav(id: String): Result<VacancyFavorite>
    suspend fun deleteAllVacFavs(): Result<Unit>
}
