package com.kutoru.jobsearch.search

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.DataManager
import com.kutoru.jobsearch.models.Offer
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {

    suspend fun setVacancyIsFavorite(id: String, favorite: Boolean): Result<Unit> {
        return if (favorite) {
            dataManager.setVacancyAsFavorite(id)
        } else {
            dataManager.unsetVacancyAsFavorite(id)
        }
    }

    suspend fun getOffers(): Result<List<Offer>> {
        return dataManager.getOffers()
    }
}
