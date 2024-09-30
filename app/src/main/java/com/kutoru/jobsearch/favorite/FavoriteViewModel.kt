package com.kutoru.jobsearch.favorite

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.main.FavoriteManager
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favManager: FavoriteManager,
) : ViewModel() {

    fun favoriteVacancy(id: String) {
        favManager.addVacancy(id)
    }

    fun unfavoriteVacancy(id: String) {
        favManager.removeVacancy(id)
    }
}
