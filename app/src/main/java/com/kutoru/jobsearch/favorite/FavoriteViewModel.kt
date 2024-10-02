package com.kutoru.jobsearch.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.DataManager
import com.kutoru.jobsearch.models.Vacancy
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {

    private val _vacancies = MutableLiveData<List<Vacancy>>(listOf())
    val vacancies: LiveData<List<Vacancy>> = _vacancies

    suspend fun reloadVacancies(): Result<Unit> {
        val result = dataManager.getVacancies(null, true)
        if (result.isFailure) {
            return Result.failure(result.exceptionOrNull()!!)
        }

        _vacancies.value = result.getOrThrow().vacancies
        return Result.success(Unit)
    }

    suspend fun setVacancyFavorite(id: String, isFavorite: Boolean): Result<Unit> {
        val result = dataManager.setVacancyFavorite(id, isFavorite)
        if (result.isFailure) {
            return result
        }

        _vacancies.value!!
            .first { it.id == id }
            .isFavorite = isFavorite

        _vacancies.value = _vacancies.value
        return result
    }
}
