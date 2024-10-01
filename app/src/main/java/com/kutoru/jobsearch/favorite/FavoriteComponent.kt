package com.kutoru.jobsearch.favorite

import dagger.Subcomponent

@Subcomponent
interface FavoriteComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)
}
