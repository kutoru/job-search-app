package com.kutoru.jobsearch.main

abstract class FavoriteUpdater {
    protected abstract val favoriteIds: MutableSet<String>

    open fun addVacancy(id: String) {
        favoriteIds.add(id)
    }

    open fun removeVacancy(id: String) {
        favoriteIds.remove(id)
    }
}
