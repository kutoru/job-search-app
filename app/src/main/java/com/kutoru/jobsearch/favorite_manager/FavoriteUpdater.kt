package com.kutoru.jobsearch.favorite_manager

abstract class FavoriteUpdater {
    protected abstract val favoriteIds: MutableSet<String>

    open fun updateVacancy(id: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteIds.add(id)
        } else {
            favoriteIds.remove(id)
        }
    }
}
