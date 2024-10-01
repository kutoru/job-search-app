package com.kutoru.jobsearch.search

import androidx.lifecycle.ViewModel
import com.kutoru.jobsearch.DataManager
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val dataManager: DataManager,
) : ViewModel() {
}
