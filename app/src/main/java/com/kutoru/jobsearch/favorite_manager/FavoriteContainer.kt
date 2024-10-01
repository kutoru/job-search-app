package com.kutoru.jobsearch.favorite_manager

import androidx.lifecycle.LiveData

interface FavoriteContainer {
    val favoriteCount: LiveData<Int>
}
