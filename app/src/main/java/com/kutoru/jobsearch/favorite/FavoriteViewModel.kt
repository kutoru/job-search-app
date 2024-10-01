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
//        dataManager.getVacancies(null, true)

        val result = dataManager.getVacancies()
        if (result.isFailure) {
            return Result.failure(result.exceptionOrNull()!!)
        }

        _vacancies.value = result.getOrThrow()
        return Result.success(Unit)
    }

    suspend fun setVacancyFavorite(id: String, isFavorite: Boolean): Result<Unit> {
//        dataManager.setVacancyFavorite()
        return Result.success(Unit)
    }
}
