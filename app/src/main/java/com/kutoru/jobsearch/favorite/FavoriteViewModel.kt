package com.kutoru.jobsearch.favorite

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.DataManager
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {
}
