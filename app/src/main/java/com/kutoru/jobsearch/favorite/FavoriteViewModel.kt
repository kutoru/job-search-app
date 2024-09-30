package com.kutoru.jobsearch.favorite

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.DataManager
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {

    suspend fun setVacancyIsFavorite(id: String, favorite: Boolean): Result<Unit> {
        return if (favorite) {
            dataManager.setVacancyAsFavorite(id)
        } else {
            dataManager.unsetVacancyAsFavorite(id)
        }
    }
}
