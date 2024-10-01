package com.kutoru.jobsearch.search

import dagger.Subcomponent

@Subcomponent
interface SearchComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    fun inject(fragment: SearchFragment)
}
