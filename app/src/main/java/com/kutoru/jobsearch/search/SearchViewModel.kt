package com.kutoru.jobsearch.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kutoru.jobsearch.DataManager
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy
import com.kutoru.jobsearch.models.VacancyResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {

    private val _offers = MutableLiveData<List<Offer>>(listOf())
    val offers: LiveData<List<Offer>> = _offers

    private val _vacancies = MutableLiveData<List<Vacancy>>(listOf())
    val vacancies: LiveData<List<Vacancy>> = _vacancies

    private val _totalVacancies = MutableLiveData(0)
    val totalVacancies: LiveData<Int> = _totalVacancies

    val expanded = MutableLiveData(false)

    suspend fun reloadAll(): Pair<Result<Unit>, Result<Unit>> {
        val (ora, vra) = listOf(
            viewModelScope.async { return@async dataManager.getOffers() },
            viewModelScope.async { return@async dataManager.getVacancies(3) },
        ).awaitAll()

        val offerResult = ora as Result<List<Offer>>
        val vacancyResult = vra as Result<VacancyResponse>

        val or = if (offerResult.isFailure) {
            Result.failure(offerResult.exceptionOrNull()!!)
        } else {
            Result.success(Unit)
        }

        val vr = if (vacancyResult.isFailure) {
            Result.failure(vacancyResult.exceptionOrNull()!!)
        } else {
            Result.success(Unit)
        }

        _offers.value = offerResult.getOrThrow()
        val vacancyResponse = vacancyResult.getOrThrow()
        _vacancies.value = vacancyResponse.vacancies
        _totalVacancies.value = vacancyResponse.total

        return Pair(or, vr)
    }

    suspend fun reloadVacancies(): Result<Unit> {
        val result = dataManager.getVacancies()
        if (result.isFailure) {
            return Result.failure(result.exceptionOrNull()!!)
        }

        val response = result.getOrThrow()
        _vacancies.value = response.vacancies
        _totalVacancies.value = response.total

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
