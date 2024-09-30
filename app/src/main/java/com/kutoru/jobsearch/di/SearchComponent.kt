package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.search.SearchFragment
import dagger.Subcomponent

@Subcomponent
interface SearchComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    fun inject(fragment: SearchFragment)
}
