package com.kutoru.jobsearch.main

import androidx.lifecycle.LiveData

interface FavoriteContainer {
    val favoriteCount: LiveData<Int>
}
