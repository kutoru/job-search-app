package com.kutoru.jobsearch.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteManager @Inject constructor() {

    private val favoriteIds = mutableSetOf<String>()
    private val _favoriteCount = MutableLiveData(0);
    val favoriteCount: LiveData<Int> = _favoriteCount;

    fun removeVacancy(id: String) {
        favoriteIds.remove(id)
        updateCount()
    }

    fun addVacancy(id: String) {
        favoriteIds.add(id)
        updateCount()
    }

    private fun updateCount() {
        if (_favoriteCount.value != favoriteIds.size) {
            _favoriteCount.value = favoriteIds.size
        }
    }
}
