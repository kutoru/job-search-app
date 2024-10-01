package com.kutoru.jobsearch.favorite_manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteManager @Inject constructor() : FavoriteContainer, FavoriteUpdater() {

    override val favoriteIds = mutableSetOf<String>()

    private val _favoriteCount = MutableLiveData(0)
    override val favoriteCount: LiveData<Int> = _favoriteCount

    override fun updateVacancy(id: String, isFavorite: Boolean) {
        super.updateVacancy(id, isFavorite)
        updateCount()
    }

    private fun updateCount() {
        if (_favoriteCount.value != favoriteIds.size) {
            _favoriteCount.value = favoriteIds.size
        }
    }
}
