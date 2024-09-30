package com.kutoru.jobsearch.search

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.main.FavoriteManager
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val favManager: FavoriteManager,
) : ViewModel() {

    fun favoriteVacancy(id: String) {
        favManager.addVacancy(id)
    }

    fun unfavoriteVacancy(id: String) {
        favManager.removeVacancy(id)
    }
}
